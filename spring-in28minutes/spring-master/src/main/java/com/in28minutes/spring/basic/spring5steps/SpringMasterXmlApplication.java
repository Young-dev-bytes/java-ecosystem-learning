package com.in28minutes.spring.basic.spring5steps;

import com.in28minutes.spring.basic.spring5steps.scope.xml.XMLPersonDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringMasterXmlApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringMasterXmlApplication.class);

    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                System.out.println(beanDefinitionName);
            }
            XMLPersonDAO xmlPersonDAO = applicationContext.getBean("xmlPersonDAO", XMLPersonDAO.class);
            LOGGER.info("{} - {}", xmlPersonDAO, xmlPersonDAO.getXmlJdbcConnection());
        }
    }

}
