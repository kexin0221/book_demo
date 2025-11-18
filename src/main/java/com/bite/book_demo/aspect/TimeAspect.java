package com.bite.book_demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.bite.book_demo.controller.*.*(..))")
    public Object timeRecord(ProceedingJoinPoint pjp) throws Throwable {
        // 1.记录开始时间
        long start = System.currentTimeMillis();
        // 2.执行目标方法
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info(pjp.getSignature().toString() + "耗时：" + (end - start) + "ms.");
        return proceed;
    }
}
