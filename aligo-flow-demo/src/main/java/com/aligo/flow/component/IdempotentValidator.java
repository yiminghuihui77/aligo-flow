package com.aligo.flow.component;

import com.aligo.flow.core.FlowContext;
import com.aligo.flow.definition.IExecutableComponent;
import org.springframework.stereotype.Component;

/**
 * 幂等校验组件
 *
 * @author minghui.y
 * @create 2022-01-04 2:19 下午
 **/
@Component
public class IdempotentValidator implements IExecutableComponent<FlowContext> {

    @Override
    public boolean condition( FlowContext context ) {
        System.out.println("IdempotentValidator condition success...");
        return true;
    }

    @Override
    public void run( FlowContext context ) {
        System.out.println("IdempotentValidator 组件进行幂等校验");
    }
}
