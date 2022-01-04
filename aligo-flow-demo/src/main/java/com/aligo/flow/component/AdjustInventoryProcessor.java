package com.aligo.flow.component;

import com.aligo.flow.core.FlowContext;
import com.aligo.flow.definition.IExecutableComponent;
import org.springframework.stereotype.Component;

/**
 * 调整库存组件
 *
 * @author minghui.y
 * @create 2022-01-04 2:24 下午
 **/
@Component
public class AdjustInventoryProcessor implements IExecutableComponent<FlowContext> {


    @Override
    public boolean condition( FlowContext context ) {
        System.out.println("AdjustInventoryProcessor condition success...");
        return true;
    }

    @Override
    public void run( FlowContext context ) {
        System.out.println("AdjustInventoryProcessor 库存调整完毕...");
    }
}
