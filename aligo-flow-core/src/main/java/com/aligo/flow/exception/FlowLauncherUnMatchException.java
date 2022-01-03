package com.aligo.flow.exception;

/**
 * @author minghui.y
 * @create 2022-01-03 4:04 下午
 **/
public class FlowLauncherUnMatchException extends AligoFlowException {
    public FlowLauncherUnMatchException() {}

    public FlowLauncherUnMatchException(String message) {
        super(message);
    }

    public FlowLauncherUnMatchException(Throwable e) {
        super(e);
    }

    public FlowLauncherUnMatchException(String message, Throwable e) {
        super(message, e);
    }
}
