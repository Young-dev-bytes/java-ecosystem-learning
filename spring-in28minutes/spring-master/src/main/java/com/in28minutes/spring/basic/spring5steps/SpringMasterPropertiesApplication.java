package com.in28minutes.spring.basic.spring5steps;

import com.in28minutes.spring.basic.spring5steps.properties.SomeExternalService;
import com.in28minutes.spring.basic.spring5steps.scope.xml.XMLPersonDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan
@PropertySource("classpath:app.properties")
public class SpringMasterPropertiesApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringMasterPropertiesApplication.class);
    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext applicationContext =
                     new AnnotationConfigApplicationContext(SpringMasterPropertiesApplication.class)) {
            SomeExternalService someExternalService = applicationContext.getBean(SomeExternalService.class);
            LOGGER.info(someExternalService.returnServiceURL());
        }
    }
}
