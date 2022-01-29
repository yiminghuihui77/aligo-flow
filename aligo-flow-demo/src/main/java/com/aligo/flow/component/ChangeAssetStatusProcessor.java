package com.aligo.flow.component;

import com.aligo.flow.core.FlowContext;
import com.aligo.flow.definition.IExecutableComponent;
import com.aligo.flow.request.AdjustStockReq;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import sun.tools.jconsole.AboutDialog;

/**
 * 变更资产状态组件
 *
 * @author minghui.y
 * @create 2022-01-29 8:44 上午
 **/
@Component
public class ChangeAssetStatusProcessor implements IExecutableComponent<FlowContext> {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean condition( FlowContext context ) {
        return true;
    }

    @Override
    public void run( FlowContext context ) {

        AdjustStockReq adjustStockReq = context.getAttribute( AdjustStockReq.class );

        if (CollectionUtils.isEmpty( adjustStockReq.getAssetList() )) {
            System.out.println("无需变更资产状态");
            return;
        }

        int updateCount = jdbcTemplate.update( "update aligo_flow_asset set is_deleted = 1, update_time = now() where asset_code in (?);", adjustStockReq.getAssetList().toArray() );

        Assert.isTrue(updateCount == adjustStockReq.getAssetList().size(), "更新资产状态失败");

        System.out.println("资产状态变更完毕");
    }
}
