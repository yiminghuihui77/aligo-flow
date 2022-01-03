package com.aligo.flow.launcher;

import com.aligo.flow.actuator.FlowActuator;
import com.aligo.flow.core.FlowContext;
import com.aligo.flow.definition.FlowDefinition;
import com.aligo.flow.exception.FlowLauncherUnMatchException;
import com.aligo.flow.holder.HolderManager;
import com.aligo.flow.identity.FlowIdentity;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 执行流启动器
 *
 * @author minghui.y
 * @create 2022-01-03 3:55 下午
 **/
@Component
public class FlowLauncher<E extends FlowIdentity, C extends FlowContext> extends AbstractLauncher<E, C> {

    private static final Logger LOGGER = LoggerFactory.getLogger( FlowLauncher.class );

    @Resource
    private HolderManager holderManager;
    @Resource
    private FlowActuator flowActuator;

    @Override
    public void fire( E flowIdentity, C flowContext ) throws Exception {
        //参数校验
        Preconditions.checkArgument( flowIdentity != null );
        Preconditions.checkArgument( flowContext != null );
        //从holder中匹配合适的执行流
        FlowDefinition flow = match(flowIdentity);
        if (flow == null) {
            LOGGER.error( "can not match flow definition with identity:{}", flowIdentity.getIdentity() );
            throw new FlowLauncherUnMatchException("can not match flow definition with identity: " + flowIdentity.getIdentity());
        }
        LOGGER.info( "matched flow: {}", flow.getIdentity() );
        //TODO ?  何用
        flowContext.setAttribute( FlowIdentity.class, flowIdentity );

        try {
            //TODO 使用actuator执行
            flowActuator.preExec( flow, flowContext );
            flowActuator.exec( flow, flowContext );
            flowActuator.postExec( flow, flowContext );

        } catch (Exception e) {
            LOGGER.error( "flow launcher run for: {} with error: {}", flow.getIdentity(), e );
            throw e;
        }
    }

    private FlowDefinition match(FlowIdentity flowIdentity) {
        //先尝试从holder中获取
        FlowDefinition matchedFlow = holderManager.getFlowHolder().matchFlow4Pattern( flowIdentity );
        if (matchedFlow != null ) {
            //如果不存在复用identity，则返回
            if (matchedFlow.getReuseIdentity() == null) {
                return matchedFlow;
            }

            //通过复用identity尝试匹配执行流
            return holderManager.getFlowHolder().matchFlow4Pattern( matchedFlow::getReuseIdentity );

        }

        return null;
    }

}
