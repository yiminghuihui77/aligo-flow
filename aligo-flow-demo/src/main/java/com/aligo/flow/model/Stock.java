package com.aligo.flow.model;

import lombok.Data;

import java.util.Date;

/**
 * 库存实体类
 *
 * @author minghui.y
 * @create 2022-01-29 9:31 上午
 **/
@Data
public class Stock {
    private Integer id;

    private String whSn;

    private String skuCode;

    private String skuName;

    private Integer insideNum;

    private Date createTime;

    private Date updateTime;
}
