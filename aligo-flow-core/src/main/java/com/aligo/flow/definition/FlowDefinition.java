package com.aligo.flow.definition;

import java.util.List;

/**
 * flow definition
 *
 * @author minghui.y
 * @create 2021-12-26 11:20 下午
 **/
public abstract class FlowDefinition extends AbstractDefinition {

    public abstract String getIdentity();
    public abstract void setIdentity(String identity);

    public abstract <E extends StepDefinition> void addStepDefinition(E stepDefinition);
    public abstract <E extends StepDefinition> List<E> getStepDefinition();

    public abstract void reuseWith(String identity);
    public abstract String getReuseIdentity();

    @Override
    public boolean equals( Object obj ) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FlowDefinition)) {
            return false;
        }
        return this.getIdentity().equals( ((FlowDefinition) obj).getIdentity() );
    }
}
