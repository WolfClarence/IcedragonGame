package com.icedragongame.common;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;

/**
 * @ClassName : BeanConvertUtils  //类名
 * @Description : haha  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/28  17:57
 */

public class BeanConvertUtils {
    public static <T> T convert(Object object,Class<T> clazz){
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
            }
        }
        return res;
    }


    private static boolean isEqualIgnoreUnderLine(String source,String target){
        source = convertUnderLineToLower(source);
        target = convertUnderLineToLower(target);
        return source.equals(target);
    }

    private static String  convertUnderLineToLower(String source){

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
                    result.append(Character.toLowerCase(currentChar));
                }
            }
        }
        return result.toString();
    }

}
