package com.aligo.flow.actuator;

import com.aligo.flow.core.FlowContext;
import com.aligo.flow.definition.StepDefinition;
import com.aligo.flow.exception.FlowExecuteException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 步骤执行器
 *
 * @author minghui.y
 * @create 2022-01-03 5:11 下午
 **/
@Component
public class StepActuator extends AbstractActuator<StepDefinition, FlowContext> {

    @Resource
    private StreamActuator streamActuator;


    @Override
    public void exec( StepDefinition definition, FlowContext context ) {
        try {
            if (CollectionUtils.isEmpty( definition.getStreamDefinitions() )) {
                throw new FlowExecuteException("StepActuator exec : " + definition.getName() + " with no stream");
            }

            Runnable runnable = () -> definition.getStreamDefinitions().forEach( streamDefinition -> {
                streamActuator.preExec( streamDefinition, context );
                streamActuator.exec( streamDefinition, context );
                streamActuator.postExec( streamDefinition, context );
            } );

            //判断step的isOpenTransaction属性，是否在一个事务内执行
            if (definition.isOpenTransaction()) {
                //获取事务模板
                //TODO


            } else {
                //不在事务内执行
                runnable.run();
            }

        } catch (Exception e) {
            execException( definition, context, e );
            throw e;
        }
    }

    @Override
    public void preExec( StepDefinition definition, FlowContext context ) {
        super.preExec( definition, context );
    }

    @Override
    public void postExec( StepDefinition definition, FlowContext context ) {
        super.postExec( definition, context );
    }
}
