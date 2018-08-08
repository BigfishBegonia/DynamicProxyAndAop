package com.testAnnotation.test.dao.impl;

import com.testAnnotation.annotations.Default;
import com.testAnnotation.annotations.Log;
import com.testAnnotation.annotations.Transaction;
import com.testAnnotation.test.dao.UserDao;


@Default
public class UserDaoImpl implements UserDao {

    @Transaction
    @Override
    public void sayHello(String hello) {
        System.out.println(hello);
    }

    @Log
    @Override
    public void sayBye(String bye) {
        System.out.println(bye);

    }

    @Transaction
    @Log
    @Override
    public void sayName(String name) {
        System.out.println("name="+name);
    }

    @Override
    public void sayAge(Integer age) {
        System.out.println("age=" + age);
    }
}
