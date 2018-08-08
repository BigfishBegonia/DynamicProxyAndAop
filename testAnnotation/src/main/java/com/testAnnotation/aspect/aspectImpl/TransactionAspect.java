package com.testAnnotation.aspect.aspectImpl;

import com.testAnnotation.aspect.Aspect;

public class TransactionAspect implements Aspect {

    @Override
    public void before() {
        System.out.println("TransactionAspect------before------");
    }

    @Override
    public void after() {
        System.out.println("TransactionAspect------after------");
    }
}
