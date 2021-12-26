package com.aligo.flow.exception;

/**
 * @author minghui.y
 * @create 2021-12-26 10:38 下午
 **/
public class AligoFlowInitException extends AligoFlowException {
    public AligoFlowInitException() {}

    public AligoFlowInitException(String message) {
        super(message);
    }

    public AligoFlowInitException(Throwable e) {
        super(e);
    }

    public AligoFlowInitException(String message, Throwable e) {
        super(message, e);
    }
}
