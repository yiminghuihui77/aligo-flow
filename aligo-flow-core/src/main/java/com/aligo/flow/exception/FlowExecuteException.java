package com.aligo.flow.exception;

/**
 * 执行异常
 *
 * @author minghui.y
 * @create 2022-01-03 5:23 下午
 **/
public class FlowExecuteException extends AligoFlowException {
    public FlowExecuteException() {}

    public FlowExecuteException(String message) {
        super(message);
    }

    public FlowExecuteException(Throwable e) {
        super(e);
    }

    public FlowExecuteException(String message, Throwable e) {
        super(message, e);
    }
}
