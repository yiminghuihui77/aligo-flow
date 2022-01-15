package com.aligo.flow.config;

import com.aligo.flow.AligoFlowBootStrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;

/**
 * @author minghui.y
 * @create 2022-01-15 12:29 下午
 **/
public class AligoFlowInitializerConfiguration implements SmartLifecycle {

    private static final Logger LOGGER = LoggerFactory.getLogger( AligoFlowInitializerConfiguration.class );

    private boolean running;

    @Autowired
    private AligoFlowBootStrap aligoFlowBootStrap;


    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop( Runnable runnable ) {
         //destroy aligo-flow context TODO
    }

    @Override
    public void start() {
        new Thread(() -> {
            try {
                LOGGER.info( "AligoFlowInitializerConfiguration initializing aligo flow context..." );
                this.aligoFlowBootStrap.flowContextInitialized();
                this.running = true;
            } catch (Exception e) {
                LOGGER.error("can not initialize aligo flow context");
            }

        }).start();
    }

    @Override
    public void stop() {
        this.running = false;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public int getPhase() {
        return 0;
    }
}
