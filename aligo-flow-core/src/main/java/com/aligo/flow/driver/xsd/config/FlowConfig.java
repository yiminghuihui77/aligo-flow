package com.aligo.flow.driver.xsd.config;

import lombok.Data;

import java.util.List;

/**
 * 一个执行流的xml配置
 *
 * @author minghui.y
 * @create 2021-12-28 9:42 下午
 **/
@Data
public class FlowConfig {

    private String name;

    private String identifier;

    private String reuseIdentifier;

    private List<StepConfig> stepConfigs;
}
