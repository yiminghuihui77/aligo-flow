package com.aligo.flow.config;

import com.aligo.flow.driver.IFlowDriver;
import lombok.Data;

/**
 * @author minghui.y
 * @create 2021-12-29 11:30 下午
 **/
@Data
public class FlowDriverConfig {

    /**
     * com.aligo.flow.constant.SupportSchemaEnum
     * 默认xml
     */
    private String schema;

    private IFlowDriver driver;

}
