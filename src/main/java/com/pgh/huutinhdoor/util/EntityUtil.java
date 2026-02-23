package com.pgh.huutinhdoor.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class EntityUtil {

    public static void copyNoNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static void copyNoNullProperties(Object source, Object target, String... ignoreProperties) {
        String[] nullProps = getNullPropertyNames(source);
        Set<String> ignoreSet = new HashSet<>();
        for (String s : nullProps) ignoreSet.add(s);
        for (String s : ignoreProperties) ignoreSet.add(s);
        BeanUtils.copyProperties(source, target, ignoreSet.toArray(new String[0]));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        return emptyNames.toArray(new String[0]);
    }

}
