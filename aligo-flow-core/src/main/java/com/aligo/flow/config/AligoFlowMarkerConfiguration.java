package com.aligo.flow.config;

import org.springframework.context.annotation.Bean;

/**
 * 负责添加要激活 [] 的标记bean
 * @author minghui.y
 * @create 2022-01-15 11:49 上午
 **/
public class AligoFlowMarkerConfiguration {

    @Bean
    public Marker aligoFlowMarkerBean() {
        return new Marker();
    }

    class Marker {
    }
}
