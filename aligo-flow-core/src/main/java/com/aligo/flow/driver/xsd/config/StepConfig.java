package com.aligo.flow.driver.xsd.config;

import lombok.Data;

import java.util.List;

/**
 * @author minghui.y
 * @create 2021-12-28 9:44 下午
 **/
@Data
public class StepConfig {

    private String name;

    private Integer priority;

    private Boolean transaction;

    private List<StreamConfig> streamConfigs;
}
