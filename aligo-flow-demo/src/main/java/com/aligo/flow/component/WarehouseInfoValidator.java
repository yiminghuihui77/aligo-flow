package com.aligo.flow.component;

import com.aligo.flow.core.FlowContext;
import com.aligo.flow.definition.IExecutableComponent;
import org.springframework.stereotype.Component;

/**
 * 仓库校验
 *
 * @author minghui.y
 * @create 2022-01-04 2:22 下午
 **/
@Component
public class WarehouseInfoValidator implements IExecutableComponent<FlowContext> {

    @Override
    public boolean condition( FlowContext context ) {
        System.out.println("WarehouseInfoValidator condition success...");
        return true;
    }

    @Override
    public void run( FlowContext context ) {
        System.out.println("WarehouseInfoValidator 组件进行仓库校验");
    }
}
