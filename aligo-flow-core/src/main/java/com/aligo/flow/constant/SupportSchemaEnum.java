package com.aligo.flow.constant;

/**
 * 执行流配置数据源枚举
 *
 * @author minghui.y
 * @create 2021-12-26 10:49 下午
 **/
public enum  SupportSchemaEnum {

    XSD("xsd", null, "xsd schema (xml)"),
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
}
