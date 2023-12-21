package com.in28minutes.learnspringframework.examples.c1;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BusinessCalculateService {


    private final DataService dataService;

    public BusinessCalculateService(@Qualifier("mongoDbDataService") DataService dataService) {
        this.dataService = dataService;
    }

    public int findMax() {
        return Arrays.stream(dataService.retrieveData()).max().orElse(0);
    }
}
