package com.aligo.flow.driver.xsd.config;

import lombok.Data;

import java.util.List;

/**
 * @author minghui.y
 * @create 2021-12-28 9:45 下午
 **/
@Data
public class StreamConfig {

    /**
     * 决定串行 or 并行执行
     */
    private String type;

    private Integer priority;

    private List<NodeConfig> nodeConfigs;

}
