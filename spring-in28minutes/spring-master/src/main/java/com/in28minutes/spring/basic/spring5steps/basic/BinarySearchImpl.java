package com.in28minutes.spring.basic.spring5steps.basic;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BinarySearchImpl {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Autowired
    @Qualifier("bubble")
    private  SortAlgorithm sortAlgorithm;


    /*public BinarySearchImpl(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }*/

    // @Autowired
    /*public void setSortAlgorithm(SortAlgorithm sortAlgorithm) {
        System.out.println("setter");
        this.sortAlgorithm = sortAlgorithm;
    }*/

    public int binarySearch(int[] numbers, int numberToSearchFor) {
        /*Implementing Sorting Logic*/

        // Bubble Sort Algorithm
        /*BubbleSortAlgorithm bubbleSortAlgorithm = new BubbleSortAlgorithm();
        int[] sortedBubble = bubbleSortAlgorithm.sort(numbers);*/

        // Quick Sort Algorithm
        // QuickSortAlgorithm quickSortAlgorithm = new QuickSortAlgorithm();


        int[] sortedBubble = sortAlgorithm.sort(numbers);

        // Search the array
        return 3;
    }

    @PostConstruct
    public void postConstructor() {
        LOGGER.info("postConstr");
    }

    @PreDestroy
    public void preDestroy() {
        LOGGER.info("preDestroy");
    }

}
