package com.xkupc.crawler.aop;

/**
 * @author xk
 * @createTime 2017/11/3 0003 下午 1:47
 * @description controller层请求输入输出日志记录
 */

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 定义controller层切入点
     */
    @Pointcut("execution(public * com.apec.customer.controller.*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //接收请求，获取请求的内容
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //记录请求的内容
        Thread current = Thread.currentThread();
        logger.info("请求:{},URL:{}", current.getId(), request.getRequestURL().toString());
        logger.info("请求:{},HTTP_METHOD:{}", current.getId(), request.getMethod());
        logger.info("请求:{},IP:{}", current.getId(), request.getRemoteAddr());
        logger.info("请求:{},CLASS_METHOD:{}", current.getId(), joinPoint.getSignature().getDeclaringTypeName());
        logger.info("请求:{},METHOD:{}", current.getId(), joinPoint.getSignature().getName());
        logger.info("请求:{},ARGS:{}", current.getId(), Arrays.toString(joinPoint.getArgs()));
        //记录执行时间
        Long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        logger.info("请求:{},RESPONSE:{}", current.getId(), JSON.toJSONString(result));
        //打印执行时间
        logger.info("请求:{},SPEND TIME:{}", current.getId(), System.currentTimeMillis() - startTime);
        return result;
    }
}
