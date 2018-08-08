package com.testAnnotation.factory;

public interface BeanFactory {
    Object getBean(Class type,String name);
    Object getBean(Class type);
}
