package com.aligo.flow.definition;

/**
 * 流程引擎元素定义：flow、step、stream、node
 * @author minghui.y
 * @create 2021-12-26 11:15 下午
 **/
public interface IDefinition extends IPriority {

    /**
     * 设置元素名称
     * @param name
     */
    void setName(String name);

    /**
     * 获取元素名称
     * @return
     */
    String getName();

    /**
     * 元素自检
     */
    void selfCheck();
}
