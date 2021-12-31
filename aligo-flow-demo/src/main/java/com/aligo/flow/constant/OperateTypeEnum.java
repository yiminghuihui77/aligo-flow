package com.aligo.flow.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * 库存操作类型: 申请/确认
 */

public enum OperateTypeEnum {

    //确认
    HANDS_ON("hands_on", "确认"),

    //申请
    APPLY("apply", "申请"),

    //撤销申请
    UNDO_APPLY("undo_apply", "撤销申请"),

    ;

    /**
     * 操作类型code
     */
    private final String code;

    /**
     * 操作类型说明
     */
    private final String desc;

    private OperateTypeEnum( String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据身份标识获取调用者实例枚举类
     *
     * @param code 身份标识
     * @return 返回调用者枚举类
     */
    public static OperateTypeEnum getInstanceByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (OperateTypeEnum item : values()) {
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
