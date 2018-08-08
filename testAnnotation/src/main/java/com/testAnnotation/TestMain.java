package com.testAnnotation;

import com.testAnnotation.factory.factoryImpl.BeanFactoryImpl;
import com.testAnnotation.test.service.Test;

public class TestMain {


    public static void main(String [] agrs){
        BeanFactoryImpl beanFactory=new BeanFactoryImpl();

         Test test=(Test) beanFactory.getBean(Test.class,"TestImpl");
           test.sayBye("bye");
           test.sayHello("hello");
           test.sayName("join");
           test.sayAge(12);
        }

}
