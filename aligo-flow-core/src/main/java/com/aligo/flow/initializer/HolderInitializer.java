package com.aligo.flow.initializer;

import com.aligo.flow.holder.HolderManager;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author minghui.y
 * @create 2021-12-31 3:54 下午
 **/
@Component
public class HolderInitializer implements Initializer{

    @Resource
    private HolderManager holderManager;


    @Override
    public <E extends ApplicationContextEvent> void initialize( E event ) {
        holderManager.initialize();
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
