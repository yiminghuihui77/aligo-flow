package com.aligo.flow.initializer;

import org.springframework.context.ApplicationContext;

/**
 * 初始化处理器
 *
 * @author minghui.y
 * @create 2021-12-26 10:02 下午
 **/
public interface Initializer {

    <E extends ApplicationContext> void initialize( E context);

    /**
     * 优先级
     * @return
     */
    default int getPriority() {
        return 1;
    }
}
