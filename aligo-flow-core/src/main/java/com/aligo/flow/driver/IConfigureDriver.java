package com.aligo.flow.driver;

/**
 * 数据源驱动器标准接口
 *
 * @author minghui.y
 * @create 2021-12-26 10:57 下午
 **/
public interface IConfigureDriver<S> {

    /**
     * 装载，从自定义数据源加载配置
     * @return
     * @throws Exception
     */
    S load() throws Exception;

    /**
     * 重载，用于运行时动态变更配置
     * @param event
     * @param message
     * @param <E>
     * @param <T>
     */
    default <E, T> void reload(E event, T message) {
    }
}
