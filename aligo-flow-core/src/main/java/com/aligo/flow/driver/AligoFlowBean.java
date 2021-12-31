package com.aligo.flow.driver;

import com.aligo.flow.definition.FlowDefinition;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * 数据结构模型契约，用于支持不同数据源加载驱动
 *
 * @author minghui.y
 * @create 2021-12-26 11:12 下午
 **/
@Data
public class AligoFlowBean implements IContractBeanSelfCheck {

    //TODO FlowDefinition重写了equals方法  这里不适合用Set，
    // 否则如果两个执行流配了相同的identity，则会被忽略，而不是报错
    private Set<FlowDefinition> flowDefinitions = new HashSet<>();

    @Override
    public boolean isAllowEmpty() {
        return false;
    }

    @Override
    public void check() {
        //TODO 对一个完整flow的校验逻辑
    }
}
