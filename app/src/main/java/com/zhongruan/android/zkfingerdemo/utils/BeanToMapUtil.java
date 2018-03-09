package com.zhongruan.android.zkfingerdemo.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import rx.android.BuildConfig;

public class BeanToMapUtil {
    public static Map<String, Object> bean2Map(Object javaBean, boolean includeNull) {
        Map<String, Object> result = new HashMap();
        for (Method method : javaBean.getClass().getDeclaredMethods()) {
            try {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    Object value = method.invoke(javaBean, (Object[]) null);
                    if (includeNull || value == null) {
                        if (value == null) {
                            value = BuildConfig.VERSION_NAME;
                        }
                        result.put(field, value);
                    } else {
                        result.put(field, value);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static Map<String, Object> bean2Map(Object javaBean) {
        return bean2Map(javaBean, false);
    }

    public static Object map2Bean(Map<String, Object> data, Class clazz) {
        Object bean = null;
        try {
            Method[] methods = clazz.getMethods();
            bean = clazz.newInstance();
            for (Method method : methods) {
                try {
                    if (method.getName().startsWith("set") && method.getParameterTypes().length == 1) {
                        String field = method.getName();
                        field = field.substring(field.indexOf("set") + 3);
                        field = field.toLowerCase().charAt(0) + field.substring(1);
                        if (data.containsKey(field) && data.get(field) != null) {
                            Class paramType = method.getParameterTypes()[0];
                            if (paramType == Integer.class || paramType == Integer.TYPE) {
                                method.invoke(bean, new Object[]{Integer.valueOf(data.get(field).toString())});
                            } else if (paramType == Boolean.class || paramType == Boolean.TYPE) {
                                method.invoke(bean, new Object[]{Boolean.valueOf(data.get(field).toString())});
                            } else if (paramType == Byte.class || paramType == Byte.TYPE) {
                                method.invoke(bean, new Object[]{Byte.valueOf(data.get(field).toString())});
                            } else if (paramType == Short.class || paramType == Short.TYPE) {
                                method.invoke(bean, new Object[]{Short.valueOf(data.get(field).toString())});
                            } else if (paramType == Character.class || paramType == Character.TYPE) {
                                method.invoke(bean, new Object[]{Character.valueOf(data.get(field).toString().charAt(0))});
                            } else if (paramType == Long.class || paramType == Long.TYPE) {
                                method.invoke(bean, new Object[]{Long.valueOf(data.get(field).toString())});
                            } else if (paramType == Float.class || paramType == Float.TYPE) {
                                method.invoke(bean, new Object[]{Float.valueOf(data.get(field).toString())});
                            } else if (paramType == Double.class || paramType == Double.TYPE) {
                                method.invoke(bean, new Object[]{Double.valueOf(data.get(field).toString())});
                            } else {
                                method.invoke(bean, new Object[]{data.get(field)});
                            }
                        }
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return bean;
    }
}
