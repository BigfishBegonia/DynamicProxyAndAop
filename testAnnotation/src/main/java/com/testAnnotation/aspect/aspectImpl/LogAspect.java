package com.testAnnotation.aspect.aspectImpl;

import com.testAnnotation.aspect.Aspect;

public class LogAspect implements Aspect {

    @Override
    public void before() {
        System.out.println("LogAspect------before------");
    }

    @Override
    public void after() {
        System.out.println("LogAspect------after------");
    }
}
