package com.aligo.flow.driver;

/**
 * 契约bean自检接口
 *
 * @author minghui.y
 * @create 2021-12-26 11:10 下午
 **/
public interface IContractBeanSelfCheck {

    default boolean isAllowEmpty() {
        return false;
    }

    void check();
}
