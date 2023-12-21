package com.in28minutes.spring.aop.springaop.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MethodExecutionCalculationAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Around(value = "com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.trackTimeAnnotation()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // execute method
        Object returnRes = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("around method : time taken by {} is {}", joinPoint, timeTaken);
        return returnRes;
    }


    @Before(value = "com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.dataLayerExecutionWithWithin()")
    public void beforeWithin(JoinPoint joinPoint) throws Throwable {

    }
}
