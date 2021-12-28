package com.aligo.flow.driver.xsd.config;

import lombok.Data;

import java.util.List;

/**
 * xml流程定义配置
 *
 * @author minghui.y
 * @create 2021-12-28 9:40 下午
 **/
@Data
public class FlowTemplateConfig {

    private String name;

    private String version;

    private List<FlowConfig> flowConfigs;
}
