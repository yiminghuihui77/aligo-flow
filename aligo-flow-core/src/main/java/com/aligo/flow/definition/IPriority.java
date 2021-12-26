package com.aligo.flow.definition;

/**
 * 优先级接口
 *
 * @author minghui.y
 * @create 2021-12-26 11:14 下午
 **/
public interface IPriority extends Comparable<IPriority> {

    void setPriority(int priority);

    int getPriority();
}
