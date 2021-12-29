package com.aligo.flow.constant;

import com.aligo.flow.driver.xsd.XsdFlowConfigureDriver;
import org.apache.commons.lang3.StringUtils;

/**
 * 执行流配置数据源枚举
 *
 * @author minghui.y
 * @create 2021-12-26 10:49 下午
 **/
public enum  SupportSchemaEnum {

    XSD("xsd", XsdFlowConfigureDriver.class, "xsd schema (xml)"),
    DB("db", null, "database schema"),
    ;

    private String code;

    private Class driverClass;

    private String desc;

    SupportSchemaEnum(String code, Class driverClass, String desc) {
        this.code = code;
        this.driverClass = driverClass;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public Class getDriverClass() {
        return driverClass;
    }

    public String getDesc() {
        return desc;
    }

    public static SupportSchemaEnum getByCode( String code ) {
        if (StringUtils.isEmpty( code )) {
            return null;
        }
        for (SupportSchemaEnum schema : SupportSchemaEnum.values()) {
            if (schema.getCode().equals( code )) {
                return schema;
            }
        }
        return null;
    }
}
