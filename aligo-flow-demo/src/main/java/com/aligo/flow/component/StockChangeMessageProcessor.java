package com.aligo.flow.component;

import com.aligo.flow.core.FlowContext;
import com.aligo.flow.definition.IExecutableComponent;
import org.springframework.stereotype.Component;

/**
 * 库存调整完成后消息通知其他域
 *
 * @author minghui.y
 * @create 2022-01-04 2:26 下午
 **/
@Component
public class StockChangeMessageProcessor implements IExecutableComponent<FlowContext> {

    @Override
    public boolean condition( FlowContext context ) {
        System.out.println("StockChangeMessageProcessor condition success...");
        return true;
    }

    @Override
    public void run( FlowContext context ) {
        System.out.println("StockChangeMessageProcessor 消息通知其他域");
    }
}
