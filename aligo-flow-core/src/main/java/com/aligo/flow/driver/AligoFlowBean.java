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
