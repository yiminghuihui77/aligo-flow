package com.aligo.flow.definition;

import com.aligo.flow.exception.AligoFlowException;

/**
 * @author minghui.y
 * @create 2021-12-26 11:35 下午
 **/
public abstract class NodeDefinition extends AbstractDefinition {

    /**
     * 开启异步
     */
    public abstract void openAsync();

    /**
     * 是否开启异步
     * @return
     */
    public abstract boolean isOpenAsync();

    /**
     * 绑定一个可执行组件
     * @param executableComponent
     */
    public abstract void binding(String executableComponent);

    /**
     * 获取可执行组件
     * @return
     */
    public abstract IExecutableComponent getExecutableComponent();

    @Override
    public void selfCheck() {
        if (this.getExecutableComponent() == null) {
            throw new AligoFlowException("Node: " + getName() + "not binding executable component.");
        }
    }
}
