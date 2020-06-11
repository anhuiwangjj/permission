package com.stone.manage.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 反射辅助工具类
 * @author wjj
 * @date 2020/6/1
 */
public class ReflectionUtils {

    /**
     * 根据方法名调用指定对象的方法
     * @param object 要调用方法的对象
     * @param method 要调用的方法名
     * @param args 参数对象数组
     * @return
     */
    public static Object invoke(Object object,String method, Object... args){
        Object result = null;
        Class<?> clazz = object.getClass();
        Method queryMethod = getMethod(clazz,method,args);
        if (Objects.nonNull(queryMethod)){
            try {
                result = queryMethod.invoke(object,args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new NoSuchMethodException(clazz.getName() + " 类中没有找到 " + method + " 方法");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据方法名和参数对象查找方法
     * @param clazz 类
     * @param name 方法名
     * @param args 参数
     * @return
     */
    public static Method getMethod(Class<?> clazz, String name, Object[] args){
        Method queryMethod = null;
        Method[] methods = clazz.getMethods();
        for (Method method: methods) {
            if (Objects.equals(name,method.getName())){
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == args.length) {
                    boolean isSameMethod = true;
                    for (int i=0;i < parameterTypes.length;i++){
                        if (!Objects.equals(parameterTypes[i],args[i].getClass())){
                            isSameMethod = false;
                        }
                    }
                    if (isSameMethod){
                        queryMethod = method;
                        break;
                    }
                }
            }
        }

        return queryMethod;
    }
}
