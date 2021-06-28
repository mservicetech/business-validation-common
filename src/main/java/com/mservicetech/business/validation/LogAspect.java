package com.mservicetech.business.validation;

import com.mservicetech.business.validation.annotations.LogAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(logAnnotation)")
    public void annotatedMethod(LogAnnotation logAnnotation) {}

    @Pointcut("@execution(* com.mservicetech.business.validation..*(..))")
    public void atExecutionForMethod() {}

    @Around("annotatedMethod(logAnnotation)")
    public Object logResponseTime(ProceedingJoinPoint jp, LogAnnotation logAnnotation) throws Throwable {
        if (!logAnnotation.isEnable()) return jp.proceed();
        long startTime = System.currentTimeMillis();
        MethodSignature ms = (MethodSignature)jp.getSignature();
        String mName = new StringBuffer(jp.getTarget().getClass().getName()).append("." + ms.getMethod().getName() )+ "";
        try {
            return jp.proceed();
        } finally {
            long completeTime = System.currentTimeMillis();
            if (logger.isInfoEnabled()) logger.info("LogAspect -- response time for method:" + mName + ":" + (completeTime - startTime) + "ms");
        }
    }
}
