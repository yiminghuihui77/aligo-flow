package com.aligo.flow.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * 步骤
 *
 * @author minghui.y
 * @create 2021-12-30 5:36 下午
 **/
public class Step extends StepDefinition{

    private boolean isOpenTransaction = false;

    private List<StreamDefinition> streamDefinitions = new ArrayList<>();

    @Override
    public void openTransaction() {
        isOpenTransaction = true;
    }

    @Override
    public boolean isOpenTransaction() {
        return isOpenTransaction;
    }

    @Override
    public <E extends StreamDefinition> void addStreamDefinition( E streamDefinition ) {
        streamDefinitions.add( streamDefinition );
    }

    @Override
    public <E extends StreamDefinition> List<E> getStreamDefinitions() {
        return (List<E>) streamDefinitions;
    }
}
