package com.in28minutes.spring.basic.spring5steps.scope.xml;

import org.springframework.stereotype.Component;

@Component
public class XMLJdbcConnection {

    public XMLJdbcConnection() {
        System.out.println("XMLJdbcConnection constructor");
    }

}
