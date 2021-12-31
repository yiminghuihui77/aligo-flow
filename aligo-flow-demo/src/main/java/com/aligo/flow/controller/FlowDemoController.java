package com.aligo.flow.controller;

import com.aligo.flow.request.AdjustStockReq;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minghui.y
 * @create 2021-12-31 4:59 下午
 **/
@RestController
@RequestMapping("/flow")
public class FlowDemoController {


    @RequestMapping("/adjustStock")
    public String adjustStock( @RequestBody AdjustStockReq req ) {

        //根据请求参数，构建流程identity

        //启动流程

        return "success";
    }

}
