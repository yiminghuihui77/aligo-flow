package com.aligo.flow.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * spring容器工具类
 *
 * @author minghui.y
 * @create 2021-12-26 10:10 下午
 **/
@Component
public class SpringContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException {
        context = applicationContext;
    }

    public static <T> Map<String, T> getImplBeans(Class<T> clazz) {
        return context.getBeansOfType( clazz );
    }

    public static <T>List<T> getImplBeanList(Class<T> clazz) {
        Map<String, T> beanMap = getImplBeans( clazz );
        if (beanMap.isEmpty()) {
            return new ArrayList<>();
        }
        return beanMap.values().stream().collect( Collectors.toList());
    }

    public static <T> Map<String, T> getBeansWithAnnotation( Class<? extends Annotation> annotationClazz ) {
        return (Map<String, T>) context.getBeansWithAnnotation( annotationClazz );
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean( clazz );
    }

    public static Object getBean(String beanName) {
        return context.getBean( LetterCaseUtils.toLowerCase( beanName ) );
    }
}
