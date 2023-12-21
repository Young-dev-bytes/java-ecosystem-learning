package com.in28minutes.spring.basic.spring5steps;

import com.in28minutes.spring.basic.spring5steps.basic.BinarySearchImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.in28minutes.spring.basic.spring5steps.basic")
public class SpringMasterBasicApplication {

    public static void main(String[] args) {

        /*BinarySearchImpl binarySearch = new BinarySearchImpl(new BubbleSortAlgorithm());
        int result = binarySearch.binarySearch(new int[]{12, 4, 6}, 3);
        System.out.println(result);*/

        // ApplicationContext applicationContext = SpringApplication.run(SpringMasterBasicApplication.class, args);
        try (AnnotationConfigApplicationContext applicationContext =
                     new AnnotationConfigApplicationContext(SpringMasterBasicApplication.class)) {
            BinarySearchImpl binarySearch = applicationContext.getBean(BinarySearchImpl.class);
            BinarySearchImpl binarySearch1 = applicationContext.getBean(BinarySearchImpl.class);
            int result = binarySearch.binarySearch(new int[]{12, 4, 6}, 3);
            System.out.println(result);
            System.out.println(binarySearch);
            System.out.println(binarySearch1);
        }
    }

}
