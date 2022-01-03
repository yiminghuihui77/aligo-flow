package com.aligo.flow.actuator;

import com.aligo.flow.core.ParamContext;
import com.aligo.flow.definition.IDefinition;
import com.aligo.flow.holder.HolderManager;

import javax.annotation.Resource;

/**
 * @author minghui.y
 * @create 2022-01-03 5:05 下午
 **/
public class AbstractActuator<E extends IDefinition, C extends ParamContext> implements IActuator<E, C> {

    @Resource
    private HolderManager holderManager;

    @Override
    public void preExec( E definition, C context ) {

    }

    @Override
    public void exec( E definition, C context ) {

    }

    @Override
    public void postExec( E definition, C context ) {

    }

    public void execException(E definition, C context, Exception e) {

    }
}
