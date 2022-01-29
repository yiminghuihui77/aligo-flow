package com.aligo.flow.model;

import lombok.Data;

import java.util.Date;

/**
 * 资产实体类
 *
 * @author minghui.y
 * @create 2022-01-29 9:32 上午
 **/
@Data
public class Asset {

    private Integer id;

    private String whSn;

    private String skuCode;

    private String assetCode;

    private Integer isDeleted;

    private Date createTime;

    private Date updateTime;

}
