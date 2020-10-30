package com.liuyang19900520.layman.starter.common.logger.aop;


import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志拦截
 *
 * @author Max Liu
 */
@Aspect
@Component
@Order(-100)
@Slf4j
public class LogRoutingAspect {
    /**
     * 使用ThreadLocal最好是每次使用完就调用remove方法，将其删掉，避免先get后set的情况导致业务的错误。
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.liuyang19900520.layman.starter.module..*.*Controller.*(..))"
    )
    private void controllerAspect() {
    }

    /**
     * controller 请求参数
     *
     * @param joinPoint 请求
     */
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) throws Exception {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            log.info("==> Request URL:" + request.getRequestURL().toString());
            log.info("==> Request Args:" + JSONUtil.toJsonStr(joinPoint.getArgs()));
        } else {
            throw new Exception("requestAttributes is null");
        }
    }

    /**
     * controller 返回内容
     *
     * @param o 返回值
     */
    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void methodAfterReturning(Object o) {
        log.info("<== Response Full Type Contents :" + JSONUtil.toJsonStr(o));
        log.info("<== Response SpendTime:" + (System.currentTimeMillis() - startTime.get()));
        startTime.remove();
    }
}
