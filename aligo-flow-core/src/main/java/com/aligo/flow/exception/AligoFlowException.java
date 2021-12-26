package com.aligo.flow.exception;

/**
 * @author minghui.y
 * @create 2021-12-26 10:35 下午
 **/
public class AligoFlowException extends RuntimeException {

    public AligoFlowException() {}

    public AligoFlowException(String message) {
        super(message);
    }

    public AligoFlowException(Throwable e) {
        super(e);
    }

    public AligoFlowException(String message, Throwable e) {
        super(message, e);
    }
}
