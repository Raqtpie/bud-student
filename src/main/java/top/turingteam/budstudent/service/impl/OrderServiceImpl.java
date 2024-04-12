package top.turingteam.budstudent.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.turingteam.budstudent.exception.CustomException;
import top.turingteam.budstudent.mapper.OrderMapper;
import top.turingteam.budstudent.pojo.entity.AdminUser;
import top.turingteam.budstudent.pojo.entity.Advertisement;
import top.turingteam.budstudent.pojo.entity.Order;
import top.turingteam.budstudent.service.AdDayAmountService;
import top.turingteam.budstudent.service.OrderService;
import top.turingteam.budstudent.support.LoginUserProvider;

import java.util.List;

/**
 * @author howe
 */
@RequiredArgsConstructor
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final LoginUserProvider loginUserProvider;

    private final OrderMapper orderMapper;

    private final AdDayAmountService adDayAmountService;

    @Override
    public List<Order> getOrderList() {
        AdminUser loginUser = loginUserProvider.getAdminLoginUser();
        if (loginUser != null && loginUser.isRole()) {
            return orderMapper.selectList(new LambdaQueryWrapper<>());
        }
        return orderMapper.selectList(new LambdaQueryWrapper<Order>().eq(Order::getUserId, StpUtil.getTokenInfo().getLoginId()));
    }

    @Override
    public void createOrder(Advertisement ad) {
        Order order = new Order();
        order.setOrderId(IdUtil.simpleUUID());
        order.setAdId(ad.getId());
        order.setUserId(ad.getPublishUserId());
        Integer tagTotalAmount = orderMapper.selectTagTotal(ad.getId());
        if (tagTotalAmount == null) {
            tagTotalAmount = 0;
        }
        Integer dayTotalAmount = adDayAmountService.getOne(new LambdaQueryWrapper<>()).getAmount();
        if (dayTotalAmount == null) {
            dayTotalAmount = 0;
        }
        order.setAmount((double) (tagTotalAmount + dayTotalAmount * ad.getPublishDays()));
        order.setPayStatus(0);
        int insert = orderMapper.insert(order);
        if (insert == 0) {
            throw new CustomException("创建订单失败");
        }
    }
}




