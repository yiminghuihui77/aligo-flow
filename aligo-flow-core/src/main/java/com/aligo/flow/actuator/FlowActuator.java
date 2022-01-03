package com.aligo.flow.actuator;

import com.aligo.flow.core.FlowContext;
import com.aligo.flow.definition.FlowDefinition;
import com.aligo.flow.exception.FlowExecuteException;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 执行流执行器
 *
 * @author minghui.y
 * @create 2022-01-03 5:07 下午
 **/
@Component
public class FlowActuator extends AbstractActuator<FlowDefinition, FlowContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger( FlowActuator.class );

    @Resource
    private StepActuator stepActuator;

    @Override
    public void exec( FlowDefinition definition, FlowContext context ) {
        //TODO
        try {
            if (CollectionUtils.isEmpty( definition.getStepDefinitions() )) {
                throw new FlowExecuteException("FlowActuator exec : " + definition.getIdentity() + " with no step");
            }
            LOGGER.info( "FlowActuator flow identity: {} start to run.", definition.getIdentity() );
            definition.getStepDefinitions().forEach( stepDefinition -> {
                stepActuator.preExec( stepDefinition, context );
                stepActuator.exec( stepDefinition, context );
                stepActuator.postExec( stepDefinition, context );
            } );
            LOGGER.info( "FlowActuator flow identity: {} run completed.", definition.getIdentity() );

        } catch (Exception e) {
            //异常处理
            execException( definition, context, e );
            throw e;
        }
    }

    /**
     * 前置处理
     * @param definition
     * @param context
     * @throws Exception
     */
    @Override
    public void preExec( FlowDefinition definition, FlowContext context ) {
        super.preExec( definition, context );
    }

    /**
     * 后置处理
     * @param definition
     * @param context
     * @throws Exception
     */
    @Override
    public void postExec( FlowDefinition definition, FlowContext context ) {
        super.postExec( definition, context );
    }
}
