package com.aligo.flow.actuator;

import com.aligo.flow.core.ParamContext;
import com.aligo.flow.definition.IDefinition;

/**
 * 执行器接口
 *
 * @author minghui.y
 * @create 2022-01-03 5:01 下午
 **/
public interface IActuator<E extends IDefinition, C extends ParamContext> {

    void preExec(E definition, C context) throws Exception;

    void exec(E definition, C context) throws Exception;

    void postExec(E definition, C context) throws Exception;

}
