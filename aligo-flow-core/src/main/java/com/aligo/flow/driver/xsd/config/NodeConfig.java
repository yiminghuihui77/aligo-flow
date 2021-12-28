package com.aligo.flow.driver.xsd.config;

import lombok.Data;

/**
 * @author minghui.y
 * @create 2021-12-28 9:47 下午
 **/
@Data
public class NodeConfig {

    private String name;

    private String executableComponent;

    private Boolean isAsync;
}
