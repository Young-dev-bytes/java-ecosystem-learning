package com.in28minutes.spring.aop.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
// @Component
@Configuration
public class UserAccessAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.businessLayerExecution()")
    public void before(JoinPoint joinPoint) {
        /*logger.info("Check for user access ");
        logger.info("Allowed execution for - {}", joinPoint);*/
    }
}
