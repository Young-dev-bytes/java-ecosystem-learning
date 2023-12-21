package com.in28minutes.spring.aop.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
// @Component
@Configuration
public class AfterAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());




    /*@AfterReturning(
            value = "execution(* com.in28minutes.spring.aop.springaop.business.*.*(..))",
            returning = "result"
    )*/
    @AfterReturning(
            value = "com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.businessLayerExecution()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("afterReturning {}, returned with value {}",
                joinPoint, result);
    }







    /*@AfterThrowing(
            value = "execution(* com.in28minutes.spring.aop.springaop.business.*.*(..))",
            throwing = "result"
    )*/
    @AfterThrowing(
            value = "com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.businessLayerExecution()",
            throwing = "exception"
    )
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        logger.info("afterThrowing {}, exception with value {}",
                joinPoint, exception);
    }








    /*@After(value = "execution(* com.in28minutes.spring.aop.springaop.business.*.*(..))")*/
    @After(value = "com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.businessLayerExecution()")
    public void after(JoinPoint joinPoint) {
        logger.info("after execution of {}", joinPoint);

    }

}
