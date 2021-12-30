package com.aligo.flow.definition;

import com.aligo.flow.util.SpringContextProvider;

/**
 * 组件节点
 *
 * @author minghui.y
 * @create 2021-12-30 5:45 下午
 **/
public class Node extends NodeDefinition{


    private boolean isOpenAsync = false;

    private IExecutableComponent executableComponent;

    @Override
    public void openAsync() {
        isOpenAsync = true;
    }

    @Override
    public boolean isOpenAsync() {
        return isOpenAsync;
    }

    @Override
    public void binding( String executableComponent ) {
        this.executableComponent = (IExecutableComponent) SpringContextProvider.getBean( executableComponent );
    }

    @Override
    public IExecutableComponent getExecutableComponent() {
        return executableComponent;
    }
}
