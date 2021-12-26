package com.aligo.flow.definition;

import org.jetbrains.annotations.NotNull;

/**
 * Definition抽象类
 *
 * @author minghui.y
 * @create 2021-12-26 11:18 下午
 **/
public abstract class AbstractDefinition implements IDefinition {

    private String name;
    private int priority;

    @Override
    public void setName( String name ) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void selfCheck() {

    }

    @Override
    public void setPriority( int priority ) {
        this.priority = priority;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo( @NotNull IPriority o ) {
        return this.priority - o.getPriority();
    }
}
