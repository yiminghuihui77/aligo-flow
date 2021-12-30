package com.aligo.flow.definition;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程
 *
 * @author minghui.y
 * @create 2021-12-30 5:30 下午
 **/
public class Flow extends FlowDefinition {

    private String identity;

    private String reuseIdentity;

    private List<StepDefinition> stepDefinitions = new ArrayList<>();


    @Override
    public String getIdentity() {
        return identity;
    }

    @Override
    public void setIdentity( String identity ) {
        this.identity = identity;
    }

    @Override
    public <E extends StepDefinition> void addStepDefinition( E stepDefinition ) {
        stepDefinitions.add( stepDefinition );
    }

    @Override
    public <E extends StepDefinition> List<E> getStepDefinitions() {
        return (List<E>) stepDefinitions;
    }

    @Override
    public void reuseWith( String identity ) {
        this.reuseIdentity = identity;
    }

    @Override
    public String getReuseIdentity() {
        return reuseIdentity;
    }
}
