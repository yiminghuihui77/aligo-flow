package com.aligo.flow.actuator;

import com.aligo.flow.core.FlowContext;
import com.aligo.flow.definition.IExecutableComponent;
import com.aligo.flow.definition.NodeDefinition;
import com.aligo.flow.util.AsyncExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 节点执行器
 *
 * @author minghui.y
 * @create 2022-01-03 5:14 下午
 **/
@Component
public class NodeActuator extends AbstractActuator<NodeDefinition, FlowContext> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger( NodeActuator.class );


    @Override
    public void exec( NodeDefinition definition, FlowContext context ) {
        IExecutableComponent executableComponent = definition.getExecutableComponent();
        Runnable runnable = () -> {
            if (executableComponent.condition(context)) {
                LOGGER.info( "NodeActuator start running node: {}", definition.getName() );
                executableComponent.run( context );
            }
        };
        //判断当前node是否需要异步执行
        if (definition.isOpenAsync()) {
            // 丢给线程池执行
            AsyncExecutor.asyncRun( runnable );
        } else {
            runnable.run();
        }
    }

    @Override
    public void preExec( NodeDefinition definition, FlowContext context ) {
        super.preExec( definition, context );
    }

    @Override
    public void postExec( NodeDefinition definition, FlowContext context ) {
        super.postExec( definition, context );
    }
}
