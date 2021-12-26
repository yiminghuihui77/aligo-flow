package com.aligo.flow.definition;

import com.aligo.flow.core.ParamContext;

import java.util.List;

/**
 * 可执行组件定义接口
 *
 * @author minghui.y
 * @create 2021-12-26 11:44 下午
 **/
public interface IExecutableComponent<E extends ParamContext> {

    /**
     * 是否执行
     * @return
     */
    default boolean condition() {
        return true;
    }

    void run(E context);

    default List<?> runSpace(E context) {
        return null;
    }
}
