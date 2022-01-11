package com.aligo.flow.config;

import com.aligo.flow.config.beans.FlowCommonConfig;
import com.aligo.flow.config.beans.GlobalConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置类
 *
 * @author minghui.y
 * @create 2021-12-26 10:46 下午
 **/
@Data
@Component
@ConfigurationProperties(prefix = "aligo.flow")
public class AligoFlowProperties {

    /**
     * 全局配置：appId,事务模板等
     */
    private GlobalConfig globalConfig = new GlobalConfig();

    /**
     * 流程相关的配置：数据源等
     */
    private FlowCommonConfig flowCommonConfig = new FlowCommonConfig();

}
