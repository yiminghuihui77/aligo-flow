package com.aligo.flow.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * Stream调度类型
 *
 * @author minghui.y
 * @create 2021-12-28 11:44 下午
 **/
public enum StreamDispatchTypeEnum {
    SEQUENCE("sequence", "串行"),
    PARALLEL("parallel", "并行"),
    ;

    private String code;
    private String desc;

    StreamDispatchTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static StreamDispatchTypeEnum getByCode(String code) {
        if (StringUtils.isEmpty( code )) {
            return null;
        }
        for (StreamDispatchTypeEnum type : StreamDispatchTypeEnum.values()) {
            if (type.code.equals( code )) {
                return type;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
