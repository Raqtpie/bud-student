package top.turingteam.budstudent.aspect;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.turingteam.budstudent.pojo.entity.SysLog;
import top.turingteam.budstudent.service.SysLogService;
import top.turingteam.budstudent.util.IPUtil;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static top.turingteam.budstudent.common.constant.ExcludePathCommon.WEBLOG_EXCLUDE;


/**
 * @author Raqtpie
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class WebLogAspect {
    private final SysLogService sysLogService;

    private final AtomicLong startTime = new AtomicLong();

    private final ThreadLocal<Object> dataHolder = new ThreadLocal<>();

    @Pointcut("execution(public * top.turingteam.budstudent.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes = Optional.ofNullable(attributes).orElseThrow(() -> new RuntimeException("attributes is null"));
        HttpServletRequest request = attributes.getRequest();
        String ip = IPUtil.getIpAddress(request);
        Operation operationAnnotation = AnnotationUtils.findAnnotation(((MethodSignature) joinPoint.getSignature()).getMethod(), Operation.class);
        String methodMark = (operationAnnotation != null) ? operationAnnotation.summary() : "";
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String reqParam = args.length == 0 ? "无参数" : Arrays.toString(args);
        SysLog sysLog = SysLog.builder()
                .ip(ip)
                .methodName(methodName)
                .methodMark(methodMark)
                .reqUrl(request.getRequestURL().toString())
                .optReqParam(reqParam)
                .build();
        excludePath(methodName, sysLog);
        dataHolder.set(sysLog);
        log.info("REQUEST URL : {} {} ", request.getMethod(), request.getRequestURL().toString());
        log.info("IP : {}", request.getRemoteAddr());
        log.debug("CLASS_METHOD : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.debug("ARGS : {}", reqParam);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()", argNames = "ret")
    public void doAfterReturning(Object ret) {
        String respContent = Optional.ofNullable(ret).map(Object::toString).orElse("");
        SysLog sysLog = (SysLog) dataHolder.get();
        if (StpUtil.getTokenInfo().getLoginId() != null
                && !"null".equals(StpUtil.getTokenInfo().getLoginId())
                && !((String) StpUtil.getTokenInfo().getLoginId()).isEmpty()) {
            Long userId = Long.valueOf((String) StpUtil.getTokenInfo().getLoginId());
            sysLog.setUserId(userId);
        }
        sysLog.setOptRespInfo(respContent);
        sysLogService.save(sysLog);
        log.info("RESPONSE : {}", respContent);
        log.debug("SPEND TIME : {} ms", (System.currentTimeMillis() - startTime.get()));
        dataHolder.remove();
    }

    public void excludePath(String methodName, SysLog sysLog) {
        for (String excludePathPattern : WEBLOG_EXCLUDE) {
            if (methodName.contains(excludePathPattern)) {
                sysLog.setOptReqParam("参数已隐藏");
                if (StpUtil.getTokenInfo().getLoginId() != null
                        && !"null".equals(StpUtil.getTokenInfo().getLoginId())
                        && !((String) StpUtil.getTokenInfo().getLoginId()).isEmpty()) {
                    Long userId = Long.valueOf((String) StpUtil.getTokenInfo().getLoginId());
                    sysLog.setUserId(userId);
                } else {
                    sysLog.setUserId(0L);
                }
            }
        }
    }
}
