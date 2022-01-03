package com.aligo.flow.launcher;

import com.aligo.flow.core.FlowContext;
import com.aligo.flow.identity.FlowIdentity;
import com.aligo.flow.util.SpringContextProvider;
import com.google.common.base.Preconditions;

/**
 * 对外暴露的流程执行器
 * @author minghui.y
 * @create 2022-01-03 4:14 下午
 **/
public class AligoFlowLauncher {

    /**
     * 以工具类静态方法形式暴露
     * @param flowIdentity
     * @param flowContext
     */
    public static void fire( FlowIdentity flowIdentity, FlowContext flowContext ) throws Exception {
        Preconditions.checkArgument( flowContext != null );
        FlowLauncher flowLauncher = SpringContextProvider.getBean( FlowLauncher.class );
        flowLauncher.fire( flowIdentity, flowContext );
    }
}
