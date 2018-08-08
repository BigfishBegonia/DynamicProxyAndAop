package com.testAnnotation.test.service.impl;

import com.testAnnotation.annotations.Extend;
import com.testAnnotation.test.dao.UserDao;
import com.testAnnotation.test.dao.impl.UserDaoImpl;
import com.testAnnotation.test.service.Test;

public class TestImpl implements UserDao,Test {
    @Extend
    UserDao userDao;


    @Override
    public void sayHello(String say) {
        userDao.sayHello(say);
    }

    @Override
    public void sayBye(String bye) {
        userDao.sayBye(bye);
    }

    @Override
    public void sayName(String name) {
       userDao.sayName(name);
    }

    @Override
    public void sayAge(Integer age) {
       userDao.sayAge(age);
    }
}
