package com.testAnnotation.factory.factoryImpl;

import com.testAnnotation.annotations.Default;
import com.testAnnotation.annotations.Extend;
import com.testAnnotation.factory.BeanFactory;
import com.testAnnotation.proxy.ProxyFactoryBean;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Properties;

public class BeanFactoryImpl implements BeanFactory{
    private String preFix="src/main/resources/";

    @Override
    public Object getBean(Class type,String name){
        String fileName = type.getName();
      if(name.length()>0){
          Properties prop = new Properties();
          try{
             FileInputStream fis = new FileInputStream(preFix+fileName+".properties");
             prop.load(fis);
             String  className = prop.getProperty(name);
             // 通过反射，获取类名对应的字节码对象
              Class classOK = Class.forName(className);
              // 通过类的字节码生成类的对象
              Object object = classOK.newInstance();
              setExtend(classOK,object);
              return this.addDynamicProxy(object);
          }catch (Exception e){
              System.out.println("properties文件打开失败");
              e.printStackTrace();
          }
      }
        return null;
    }
   @Override
    public Object getBean(Class type){
       String fileName = type.getName();
       Properties prop=new Properties();

       try {
           FileInputStream fileInputStream = new FileInputStream(preFix+fileName + ".properties");
           prop.load(fileInputStream);

           String [] strArr=prop.values().toArray(new String[]{});

           if (strArr.length <=0) {
               System.out.println("实体类不存在,bean创建失败");
               return null;
           }
           Class classDefault = null;

           for (int i=0;i< strArr.length;i++) {
               try {
                   Class<?> loadClass = Class.forName(strArr[i]);
                   if (loadClass.getAnnotation(Default.class) != null) {
                       classDefault = loadClass;
                   }
               } catch (ClassNotFoundException e) {
                   System.out.println("bean配置文件出错");
                   e.printStackTrace();
               }
           }

           if (classDefault != null) {
               try {
                   classDefault = Class.forName(strArr[0]);
               } catch (ClassNotFoundException e) {
                   e.printStackTrace();
               }
           }
           Object object = null;
           object = classDefault.newInstance();
           setExtend(classDefault,object);
           return this.addDynamicProxy(object);
       }catch (Exception e){
           e.printStackTrace();
       }
        return null;
    }

    public void setExtend(Class classExtend,Object obj){

        try {
            for (Field field : classExtend.getDeclaredFields()) {
                if (field.getAnnotation(Extend.class) != null) {
                    field.setAccessible(true);
                    Object filedBean = getBean(field.getType());
                    field.set(obj, filedBean);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public Object addDynamicProxy(Object object){
        InvocationHandler invokeHandler=new ProxyFactoryBean(object);
        Object reObject = Proxy.newProxyInstance(invokeHandler.getClass().getClassLoader(),object
                .getClass().getInterfaces(), invokeHandler);
        return reObject;
    }

}
