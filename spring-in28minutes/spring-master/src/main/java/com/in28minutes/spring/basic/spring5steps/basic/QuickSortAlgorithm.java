package com.in28minutes.spring.basic.spring5steps.basic;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
// @Primary
@Qualifier("quick")
public class QuickSortAlgorithm implements SortAlgorithm {

    @Override
    public int[] sort(int[] numbers) {
        // logic for bubble sort

        System.out.println("quick numbers");
        return numbers;
    }
}
