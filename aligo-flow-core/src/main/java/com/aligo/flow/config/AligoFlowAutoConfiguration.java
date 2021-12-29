package com.aligo.flow.config;

import com.aligo.flow.constant.SupportSchemaEnum;
import com.aligo.flow.driver.IFlowDriver;
import com.aligo.flow.exception.AligoFlowInitException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 配置类
 *
 * @author minghui.y
 * @create 2021-12-29 11:27 下午
 **/
@Configuration
public class AligoFlowAutoConfiguration {

    @Resource
    private AligoFlowProperties aligoFlowProperties;

    @Bean
    public FlowDriverConfig flowDriverConfig() {
        FlowDriverConfig driverConfig = new FlowDriverConfig();
        driverConfig.setSchema( aligoFlowProperties.getSchema() );
        try {
            SupportSchemaEnum schema = SupportSchemaEnum.getByCode( aligoFlowProperties.getSchema() );
            driverConfig.setDriver( (IFlowDriver) schema.getDriverClass().newInstance() );

        } catch (Exception e) {
            throw new AligoFlowInitException("AligoFlowAutoConfiguration config with error:" + e.getMessage());
        }

        return driverConfig;
    }
}
