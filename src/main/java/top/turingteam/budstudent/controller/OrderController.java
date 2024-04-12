package top.turingteam.budstudent.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.easysdk.factory.Factory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.turingteam.budstudent.common.constant.ResultCode;
import top.turingteam.budstudent.common.constant.UserType;
import top.turingteam.budstudent.config.AliPayConfig;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.pojo.entity.Order;
import top.turingteam.budstudent.pojo.vo.Result;
import top.turingteam.budstudent.service.OrderService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author howe
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "广告订单管理模块")
public class OrderController {

    private static final String GATEWAY_URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private static final String FORMAT = "JSON";
    private static final String CHARSET = "utf-8";
    private static final String SIGN_TYPE = "RSA2";

    private final AliPayConfig aliPayConfig;

    private final OrderService orderService;

    @SaCheckRole(value = {UserType.SUPER_ADMIN_STR, UserType.MERCHANT_STR}, mode = SaMode.OR)
    @GetMapping()
    @Operation(summary = "查看广告订单列表", description = "超级管理员权限会得到所有广告订单列表，商家会得到自己的订单列表")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result list() {
        return Result.builder().code(ResultCode.SUCCESS.code).msg("查询成功").data(orderService.getOrderList()).build();
    }

    /**
     * 用户支付订单
     */
    @SaCheckRole(UserType.MERCHANT_STR)
    @GetMapping("/{adId}")
    @Operation(summary = "支付订单", description = "商家支付自己的订单，会跳转到支付宝沙盒环境")
    @Parameter(name = "Authorization", description = "Token", in = ParameterIn.HEADER, schema = @Schema(type = "string"), required = true)
    public Result pay(@Parameter(description = "广告id", required = true) @PathVariable Integer adId, HttpServletResponse httpResponse) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getAdId, adId);
        Order order = orderService.getOne(queryWrapper);
        if (order == null) {
            return Result.builder().code(ResultCode.ERROR.code).msg("订单不存在").build();
        }
        if (order.getPayStatus() == 1) {
            return Result.builder().code(ResultCode.SUCCESS.code).msg("订单已支付").build();
        }
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        request.setBizContent("{\"out_trade_no\":\"" + order.getOrderId() + "\","
                + "\"total_amount\":\"" + order.getAmount() + "\","
                + "\"subject\":\"" + "广告推广费" + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            throw new CustomException("支付表单生成失败");
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        // 直接将完整的表单html输出到页面
        try {
            httpResponse.getWriter().write(form);
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        } catch (IOException e) {
            throw new CustomException("支付宝页面输出失败");
        }
        return Result.builder().code(ResultCode.SUCCESS.code).build();
    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    @Operation(summary = "支付宝异步回调接口", description = "不用管这个接口，这是支付宝收到付款后回调本服务进行订单状态更新的")
    public Result payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }

            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");
            // 支付宝验签
            if (Factory.Payment.Common().verifyNotify(params)) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
                // 更新订单已支付的逻辑代码
                Order order = new Order();
                order.setOrderId(tradeNo);
                order.setPayStatus(1);
                orderService.updateById(order);
            }

            System.out.println("============异步回调结束============");
        }
        return Result.builder().code(ResultCode.SUCCESS.code).build();
    }


}
