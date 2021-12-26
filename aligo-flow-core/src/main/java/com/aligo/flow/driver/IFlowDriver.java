package com.aligo.flow.driver;

import com.aligo.flow.factory.IDefinitionModelFactory;

/**
 * 执行流驱动器
 *
 * @author minghui.y
 * @create 2021-12-26 11:00 下午
 **/
public interface IFlowDriver<S, M> extends IConfigureDriver<S> {


    /**
     * 对数据源进行建模，转换为引擎标准模型
     * @param modelFactory
     * @param sourceData 数据源
     * @return
     * @throws Exception
     */
    M modeling( IDefinitionModelFactory modelFactory, S sourceData ) throws Exception;
}
