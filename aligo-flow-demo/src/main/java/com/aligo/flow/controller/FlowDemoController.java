package com.aligo.flow.controller;

import com.aligo.flow.core.FlowContext;
import com.aligo.flow.identity.FlowIdentity;
import com.aligo.flow.launcher.AligoFlowLauncher;
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


    /**
     * {
     *     "adjustNum":10,
     *     "bizLine":"LEASEEV_BIKE",
     *     "operateType":"apply",
     *     "operatorId":"huihui",
     *     "operatorName":"灰灰",
     *     "sceneType":"outbound",
     *     "skuCode":"1040112",
     *     "whAreaSn":"whArea001",
     *     "whSn":"wh001"
     * }
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping("/adjustStock")
    public String adjustStock( @RequestBody AdjustStockReq req ) throws Exception {

//        根据请求参数，构建流程identity
        FlowIdentity flowIdentity = buildIdentity( req );

        //构建上下文
        FlowContext flowContext = new FlowContext();
        flowContext.addAttribute( AdjustStockReq.class, req );

        //启动流程
        AligoFlowLauncher.fire( flowIdentity, flowContext );

        return "success";
    }


    /**
     * 根据请求参数构建flowIdentity
     * @param req
     * @return
     */
    private FlowIdentity buildIdentity(AdjustStockReq req) {
        return FlowIdentity.builder()
                .idSlice( req.getBizLine() )
                .idSlice( req.getSceneType() )
                .idSlice( req.getOperateType() )
                .build();
    }

}
