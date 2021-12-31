package com.aligo.flow.holder;

import com.aligo.flow.definition.FlowDefinition;
import com.aligo.flow.identity.FlowIdentity;
import org.springframework.stereotype.Component;

/**
 * holder管理器
 *
 * @author minghui.y
 * @create 2021-12-31 3:48 下午
 **/
@Component
public class HolderManager {

    private volatile boolean hasInitialized = false;

    /**
     * 流程容器
     */
    private FlowHolder<FlowDefinition> flowHolder = new FlowHolder<>();

    /**
     * 初始化
     */
    public synchronized void initialize() {
        if (hasInitialized) {
            return;
        }
        flowHolder.construct();
        hasInitialized = true;
    }

    /**
     * 添加执行流
     * @param flowIdentity
     * @param flowDefinition
     */
    public void addFlow( FlowIdentity flowIdentity, FlowDefinition flowDefinition) {
        flowHolder.getFlowContainer().put( new FlowHolder.DefaultFlowIdentity( flowIdentity ), flowDefinition );
    }

}
