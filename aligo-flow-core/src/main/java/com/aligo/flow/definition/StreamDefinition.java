package com.aligo.flow.definition;

import java.util.List;

/**
 * @author minghui.y
 * @create 2021-12-26 11:32 下午
 **/
public abstract class StreamDefinition extends AbstractDefinition {

    /**
     * 开启并发执行
     */
    public abstract void openParallel();

    /**
     * 是否开启并发执行
     * @return
     */
    public abstract boolean isOpenParallel();

    /**
     * 向stream添加一个node
     * @param nodeDefinition
     * @param <E>
     */
    public abstract <E extends NodeDefinition> void addNodeDefinition(E nodeDefinition);

    /**
     * 获取stream中的所有node
     * @param <E>
     * @return
     */
    public abstract <E extends  NodeDefinition> List<E> getNodeDefinitions();
}
