package com.aligo.flow.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

/**
 * 执行流初始化器
 *
 * @author minghui.y
 * @create 2021-12-26 10:05 下午
 **/
@Component
public class FlowInitializer implements Initializer {

    private static final Logger LOGGER = LoggerFactory.getLogger( FlowInitializer.class );

    @Override
    public <E extends ApplicationContextEvent> void initialize( E event ) {

    }

    @Override
    public int getPriority() {
        return 0;
    }
}
