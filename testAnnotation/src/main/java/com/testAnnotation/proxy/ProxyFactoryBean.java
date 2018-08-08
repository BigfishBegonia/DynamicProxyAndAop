package com.testAnnotation.proxy;

import com.testAnnotation.annotations.Log;
import com.testAnnotation.annotations.Transaction;
import com.testAnnotation.aspect.Aspect;
import com.testAnnotation.aspect.aspectImpl.LogAspect;
import com.testAnnotation.aspect.aspectImpl.TransactionAspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyFactoryBean implements InvocationHandler {

    private Object obj;

    public ProxyFactoryBean(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method localMethod=null;
        try {
            localMethod = obj.getClass().getMethod(method.getName(), method.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Transaction transactionAnnotation = localMethod.getAnnotation(Transaction.class);
        Aspect transactionAspect =null;
        if (transactionAnnotation!=null) {
            transactionAspect = new TransactionAspect();
            transactionAspect.before();
        }

        Log logAnnotation = localMethod.getAnnotation(Log.class);
        Aspect logAspect =null;
        if (logAnnotation!=null) {
            logAspect = new LogAspect();
            logAspect.before();
        }
        Object result = null;
        try {
            result = localMethod.invoke(obj, args);
            if (transactionAnnotation!=null) {
                transactionAspect.after();
            }
        } catch (Exception e) {
            System.out.println("transactionAspect");
            e.printStackTrace();
        }finally {
            if (logAnnotation!=null) {
                logAspect.after();
            }
        }
        return null;
    }
}
