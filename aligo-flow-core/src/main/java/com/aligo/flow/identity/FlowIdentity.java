package com.aligo.flow.identity;

/**
 * 流程标识
 * 由具体应用方实现
 * @author minghui.y
 * @create 2021-12-31 3:15 下午
 **/
public interface FlowIdentity {

    /**
     * 获取流程标识
     * @return
     */
    String getIdentity();

}
