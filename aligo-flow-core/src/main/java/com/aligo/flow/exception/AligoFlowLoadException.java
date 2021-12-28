package com.aligo.flow.exception;

/**
 * 加载异常
 *
 * @author minghui.y
 * @create 2021-12-28 9:59 下午
 **/
public class AligoFlowLoadException extends AligoFlowException {
    public AligoFlowLoadException() {}

    public AligoFlowLoadException(String message) {
        super(message);
    }

    public AligoFlowLoadException(Throwable e) {
        super(e);
    }

    public AligoFlowLoadException(String message, Throwable e) {
        super(message, e);
    }
}
