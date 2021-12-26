package com.aligo.flow.definition;

import java.util.List;

/**
 * @author minghui.y
 * @create 2021-12-26 11:28 下午
 **/
public abstract class StepDefinition extends AbstractDefinition {

    /**
     * step 开启事务
     */
    public abstract void openTransaction();

    /**
     * 是否开启事务
     * @return
     */
    public abstract boolean isOpenTransaction();

    /**
     * 向step添加一个stream
     * @param streamDefinition
     * @param <E>
     */
    public abstract <E extends StreamDefinition> void addStreamDefinition(E streamDefinition);

    /**
     * 获取step中的所有stream
     * @param <E>
     * @return
     */
    public abstract <E extends StreamDefinition> List<E> getStreamDefinitions();
}
