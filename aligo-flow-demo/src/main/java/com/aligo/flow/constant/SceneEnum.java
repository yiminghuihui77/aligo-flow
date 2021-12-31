package com.aligo.flow.constant;


import org.apache.commons.lang3.StringUtils;

/**
 * 库存场景 出库/入库
 */
public enum SceneEnum {
    OUTBOUND("outbound", "出库"),
    INBOUND("inbound", "入库"),
    ;

    /**
     * 场景code
     */
    private final String code;

    /**
     * 场景说明
     */
    private final String desc;

    private SceneEnum( String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据身份标识获取调用者实例枚举类
     *
     * @param code 身份标识
     * @return 返回调用者枚举类
     */
    public static SceneEnum getInstanceByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (SceneEnum item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Getter method for property code.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property desc.
     *
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }
}
