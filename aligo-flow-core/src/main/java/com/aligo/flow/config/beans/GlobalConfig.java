package com.aligo.flow.config.beans;

import lombok.Data;

/**
 * aligo-flow全局配置
 *
 * @author minghui.y
 * @create 2022-01-10 4:31 下午
 **/
@Data
public class GlobalConfig {
    /**
     * app id
     */
    private String appId;
    /**
     * 事务模板 bean name
     */
    private String transactionTemplateBeanName;
}
