package com.aligo.flow.core;

import java.util.List;

/**
 * 参数上下文
 *
 * @author minghui.y
 * @create 2021-12-26 11:44 下午
 **/
public interface ParamContext {

    <T> T addAttribute(Class<T> clazz, Object object);

    <T> T setAttribute(Class<T> clazz, Object object);

    <T> T getAttribute(Class<T> clazz);

    <T> T removeAttribute(Class<T> clazz);


    <T> List<T> addListAttribute(Class<T> clazz, List<T> list);

    <T> List<T> setListAttribute(Class<T> clazz, List<T> list);

    <T> List<T> getListAttribute(Class<T> clazz);

    <T> T addAttribute(T object);

    Object addAttribute(String key, Object value);

    Object setAttribute(String key, Object value);

    Object getAttribute(String key);


}
