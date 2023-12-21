package com.in28minutes.spring.basic.spring5steps.basic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
// @Primary
@Qualifier("bubble")
public class BubbleSortAlgorithm implements SortAlgorithm {

    @Override
    public int[] sort(int[] numbers) {
        // logic for bubble sort

        System.out.println("bubble numbers");
        return numbers;
    }
}
