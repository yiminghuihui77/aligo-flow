package com.aligo.flow;

import com.aligo.flow.annotation.EnableAligoFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author minghui.y
 * @create 2021-12-31 4:52 下午
 **/
@SpringBootApplication
//aligo-flow 启动必备注解
@EnableAligoFlow
public class FlowDemoApp {

    private static final Logger LOGGER = LoggerFactory.getLogger( FlowDemoApp.class );

    public static void main( String[] args ) {
        SpringApplication.run( FlowDemoApp.class, args );
        LOGGER.info( "FlowDemoApp started up success..." );
    }
}
