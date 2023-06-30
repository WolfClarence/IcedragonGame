package com.icedragongame.utils;

import com.icedragongame.entity.User;

import java.lang.reflect.Field;

/**
 * @ClassName : BeanConvertUtils  //类名
 * @Description : haha  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/28  17:57
 */

public class MyBeanUtils {

    /**
     * 实现两个类的同名同类型字段的值复制,并返回第二个参数所决定的类型值
     * @param object 数据源
     * @param clazz  目标数据类型
     * @param isIgnoreUnderLineAndSmallCamel  是否忽略下划线和小驼峰命名的区别
     * @return 返回 T
     * @param <T> 返回回值
     */
    public static <T> T beanCopy(Object object, Class<T> clazz,boolean isIgnoreUnderLineAndSmallCamel){
        T res=null;
        try {
             res = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Field[] sourceFields = object.getClass().getDeclaredFields();
        Field[] targetFields = clazz.getDeclaredFields();
        for (Field sourceField : sourceFields) {
            for (Field targetField : targetFields) {
                if(isIgnoreUnderLineAndSmallCamel){
                    if (isEqualIgnoreUnderLine(sourceField.getName(),targetField.getName() )&& sourceField.getType() == targetField.getType()) {
                        try {
                            sourceField.setAccessible(true);
                            targetField.setAccessible(true);
                            Object value = sourceField.get(object);
                            targetField.set(res, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    if (sourceField.getName().equals(targetField.getName())&& sourceField.getType() == targetField.getType()) {
                        try {
                            sourceField.setAccessible(true);
                            targetField.setAccessible(true);
                            Object value = sourceField.get(object);
                            targetField.set(res, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
        return res;
    }


    /**
     * 判断在忽略小驼峰和下划线区别的情况下两个字符串是否相同
     * @param source param1
     * @param target param2
     * @return boolean
     */
    private static boolean isEqualIgnoreUnderLine(String source,String target){
        source = convertUnderLineToSmallCamel(source);
        target = convertUnderLineToSmallCamel(target);
        return source.equals(target);
    }


    /**
     * 将下划线命名方式转为小驼峰命名方式
     * @param source param
     * @return 小驼峰命名方式的值
     */
    private static String convertUnderLineToSmallCamel(String source){

        StringBuilder result = new StringBuilder();
        boolean toUpper = false;
        for (int i = 0; i < source.length(); i++) {
            char currentChar = source.charAt(i);
            if (currentChar == '_') {
                toUpper = true;
            } else {
                if (toUpper) {
                    result.append(Character.toUpperCase(currentChar));
                    toUpper = false;
                } else {
                    result.append(currentChar);
                }
            }
        }
        return result.toString();
    }

    public static void initBean(Object bean) {
        Field[] sourceFields = bean.getClass().getDeclaredFields();
        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            Class<?> type = sourceField.getType();
            if(type.equals(String.class)){
                try {
                    Object o = sourceField.get(bean);
                    if(o==null){
                        sourceField.set(bean,"默认值");
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }else{
                try {
                    Object o = sourceField.get(bean);
                    if(o==null){
                        sourceField.set(bean,0);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
