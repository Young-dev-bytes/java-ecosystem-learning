package com.in28minutes.spring.basic.spring5steps;

import com.in28minutes.spring.basic.componentscan.ComponentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = "com.in28minutes.spring.basic.componentscan")
public class SpringMasterComponentScanApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringMasterComponentScanApplication.class);

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringMasterComponentScanApplication.class)) {
            ComponentDAO componentDAO = applicationContext.getBean(ComponentDAO.class);
            ComponentDAO componentDAO2 = applicationContext.getBean(ComponentDAO.class);
            LOGGER.info("{}", componentDAO);
            LOGGER.info("{}", componentDAO.getComponentJdbcConnection());
            LOGGER.info("{}", componentDAO2);
            LOGGER.info("{}", componentDAO2.getComponentJdbcConnection());
        }
    }

}


