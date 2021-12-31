package com.aligo.flow.request;

import lombok.Data;

/**
 * 调整库存请求
 *
 * @author minghui.y
 * @create 2021-12-31 5:03 下午
 **/
@Data
public class AdjustStockReq {

    /**
     * 业务线
     */
    private String bizLine;

    /**
     * 场景：出库、入库
     * com.aligo.flow.constant.SceneEnum
     */
    private String sceneType;

    /**
     * 操作类型
     * com.aligo.flow.constant.OperateTypeEnum
     */
    private String operateType;

    /**
     * 仓库编码
     */
    private String whSn;

    /**
     * 库区编码
     */
    private String whAreaSn;

    /**
     * 物料编码
     */
    private String skuCode;

    /**
     * 调整数量
     */
    private Integer adjustNum;

    /**
     * 操作人id
     */
    private String operatorId;
    /**
     * 操作人姓名
     */
    private String operatorName;

}
