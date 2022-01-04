package com.aligo.flow.request;

import java.io.Serializable;

/**
 * 调整库存请求
 *
 * @author minghui.y
 * @create 2021-12-31 5:03 下午
 **/
public class AdjustStockReq implements Serializable {

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




    /**
     * Getter method for property bizLine.
     *
     * @return property value of bizLine
     */
    public String getBizLine() {
        return bizLine;
    }

    /**
     * Setter method for property bizLine.
     *
     * @param bizLine value to be assigned to property bizLine
     */
    public void setBizLine( String bizLine ) {
        this.bizLine = bizLine;
    }

    /**
     * Getter method for property sceneType.
     *
     * @return property value of sceneType
     */
    public String getSceneType() {
        return sceneType;
    }

    /**
     * Setter method for property sceneType.
     *
     * @param sceneType value to be assigned to property sceneType
     */
    public void setSceneType( String sceneType ) {
        this.sceneType = sceneType;
    }

    /**
     * Getter method for property operateType.
     *
     * @return property value of operateType
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * Setter method for property operateType.
     *
     * @param operateType value to be assigned to property operateType
     */
    public void setOperateType( String operateType ) {
        this.operateType = operateType;
    }

    /**
     * Getter method for property whSn.
     *
     * @return property value of whSn
     */
    public String getWhSn() {
        return whSn;
    }

    /**
     * Setter method for property whSn.
     *
     * @param whSn value to be assigned to property whSn
     */
    public void setWhSn( String whSn ) {
        this.whSn = whSn;
    }

    /**
     * Getter method for property whAreaSn.
     *
     * @return property value of whAreaSn
     */
    public String getWhAreaSn() {
        return whAreaSn;
    }

    /**
     * Setter method for property whAreaSn.
     *
     * @param whAreaSn value to be assigned to property whAreaSn
     */
    public void setWhAreaSn( String whAreaSn ) {
        this.whAreaSn = whAreaSn;
    }

    /**
     * Getter method for property skuCode.
     *
     * @return property value of skuCode
     */
    public String getSkuCode() {
        return skuCode;
    }

    /**
     * Setter method for property skuCode.
     *
     * @param skuCode value to be assigned to property skuCode
     */
    public void setSkuCode( String skuCode ) {
        this.skuCode = skuCode;
    }

    /**
     * Getter method for property adjustNum.
     *
     * @return property value of adjustNum
     */
    public Integer getAdjustNum() {
        return adjustNum;
    }

    /**
     * Setter method for property adjustNum.
     *
     * @param adjustNum value to be assigned to property adjustNum
     */
    public void setAdjustNum( Integer adjustNum ) {
        this.adjustNum = adjustNum;
    }

    /**
     * Getter method for property operatorId.
     *
     * @return property value of operatorId
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * Setter method for property operatorId.
     *
     * @param operatorId value to be assigned to property operatorId
     */
    public void setOperatorId( String operatorId ) {
        this.operatorId = operatorId;
    }

    /**
     * Getter method for property operatorName.
     *
     * @return property value of operatorName
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * Setter method for property operatorName.
     *
     * @param operatorName value to be assigned to property operatorName
     */
    public void setOperatorName( String operatorName ) {
        this.operatorName = operatorName;
    }
}
