package com.aligo.flow.config.beans;

import com.aligo.flow.constant.SupportSchemaEnum;
import lombok.Data;

/**
 * 流程相关配置
 *
 * @author minghui.y
 * @create 2022-01-10 4:35 下午
 **/
@Data
public class FlowCommonConfig {

    /**
     * 数据源类型
     */
    private String schema = SupportSchemaEnum.XSD.getCode();


}
