package com.aligo.flow.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * flow上下文用于组件之间数据传递
 *
 * @author minghui.y
 * @create 2022-01-03 3:33 下午
 **/
@SuppressWarnings( "unchecked" )
public class FlowContext implements ParamContext {

    /** key为Object的class */
    private final Map<Class, Object> attributes = Maps.newConcurrentMap();

    /** key为List元素的class */
    private final Map<Class, List> listAttributes = Maps.newConcurrentMap();


    @Override
    public <T> T addAttribute( Class<T> clazz, Object object ) {
        Preconditions.checkArgument( clazz == object.getClass(),
                "object class:%s is different with expected class:%s",
                clazz, object.getClass() );
        return (T) attributes.putIfAbsent( clazz, object );
    }

    @Override
    public <T> T setAttribute( Class<T> clazz, Object object ) {
        Preconditions.checkArgument( clazz == object.getClass(),
                "object class:%s is different with expected class:%s",
                clazz, object.getClass() );
        return (T) attributes.put( clazz, object );
    }

    @Override
    public <T> T getAttribute( Class<T> clazz ) {
        return (T) attributes.get( clazz );
    }

    @Override
    public <T> T removeAttribute( Class<T> clazz ) {
        return (T) attributes.remove( clazz );
    }

    @Override
    public <T> List<T> addListAttribute( Class<T> clazz, List<T> list ) {
        CollectionUtils.isNotEmpty( list );
        return listAttributes.putIfAbsent( clazz, list );
    }

    @Override
    public <T> List<T> setListAttribute( Class<T> clazz, List<T> list ) {
        CollectionUtils.isNotEmpty( list );
        return listAttributes.put( clazz, list );
    }

    @Override
    public <T> List<T> getListAttribute( Class<T> clazz ) {
        return listAttributes.get( clazz );
    }

    @Override
    public <T> T addAttribute( T object ) {
        return (T) addAttribute( object.getClass(), object );
    }

    @Override
    public Object addAttribute( String key, Object value ) {
        return null;
    }

    @Override
    public Object setAttribute( String key, Object value ) {
        return null;
    }

    @Override
    public Object getAttribute( String key ) {
        return null;
    }
}
