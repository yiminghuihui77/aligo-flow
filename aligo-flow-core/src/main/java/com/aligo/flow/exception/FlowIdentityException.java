package com.aligo.flow.exception;

/**
 * 流程标识相关异常
 *
 * @author minghui.y
 * @create 2022-01-15 11:13 上午
 **/
public class FlowIdentityException extends AligoFlowException {
    public FlowIdentityException() {}

    public FlowIdentityException(String message) {
        super(message);
    }

    public FlowIdentityException(Throwable e) {
        super(e);
    }

    public FlowIdentityException(String message, Throwable e) {
        super(message, e);
    }
}
