package com.aligo.flow.config;

import com.aligo.flow.constant.SupportSchemaEnum;
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
     * 数据源类型
     */
    private String schema = SupportSchemaEnum.XSD.getCode();

}
