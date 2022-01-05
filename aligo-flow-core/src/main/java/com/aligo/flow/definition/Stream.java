package com.aligo.flow.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * 执行流
 *
 * @author minghui.y
 * @create 2021-12-30 5:38 下午
 **/
public class Stream extends StreamDefinition{

    private boolean isOpenParallel = false;

    private List<NodeDefinition> nodeDefinitions = new ArrayList<>();

    @Override
    public void openParallel() {
        isOpenParallel = true;
    }

    @Override
    public boolean isOpenParallel() {
        return isOpenParallel;
    }

    @Override
    public <E extends NodeDefinition> void addNodeDefinition( E nodeDefinition ) {
        nodeDefinitions.add( nodeDefinition );
    }

    @Override
    public <E extends NodeDefinition> List<E> getNodeDefinitions() {
        return (List<E>) nodeDefinitions;
    }
}
