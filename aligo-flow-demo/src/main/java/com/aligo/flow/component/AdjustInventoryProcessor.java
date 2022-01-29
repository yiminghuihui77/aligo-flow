package com.aligo.flow.component;

import com.aligo.flow.core.FlowContext;
import com.aligo.flow.definition.IExecutableComponent;
import com.aligo.flow.model.Stock;
import com.aligo.flow.request.AdjustStockReq;
import javafx.util.converter.NumberStringConverter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 调整库存组件
 *
 * @author minghui.y
 * @create 2022-01-04 2:24 下午
 **/
@Component
public class AdjustInventoryProcessor implements IExecutableComponent<FlowContext> {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public boolean condition( FlowContext context ) {
        System.out.println("AdjustInventoryProcessor condition success...");
        return true;
    }

    @Override
    public void run( FlowContext context ) {

        AdjustStockReq adjustStockReq = context.getAttribute( AdjustStockReq.class );

        //查询库存表
        List<Stock> stockList = jdbcTemplate.query( "select * from aligo_flow_stock where wh_sn = ? and sku_code = ?;",
                new RowMapper<Stock>() {
                    @Override
                    public Stock mapRow( ResultSet resultSet, int i ) throws SQLException {
                        Stock stock = new Stock();
                        stock.setId( resultSet.getInt( "id" ) );
                        stock.setWhSn( resultSet.getString( "wh_sn" ) );
                        stock.setSkuCode( resultSet.getString( "sku_code" ) );
                        stock.setSkuName( resultSet.getString( "sku_name" ) );
                        stock.setInsideNum( resultSet.getInt( "inside_num" ) );
                        stock.setCreateTime( resultSet.getDate( "create_time" ) );
                        stock.setUpdateTime( resultSet.getDate( "update_time" ) );
                        return stock;
                    }
                }, adjustStockReq.getWhSn(), adjustStockReq.getSkuCode() );

        Assert.isTrue( CollectionUtils.isNotEmpty( stockList ), "不存在对应库存记录：" + adjustStockReq.getWhSn() + ":" + adjustStockReq.getSkuCode());

        //更新库存
        Stock stock = stockList.get( 0 );
        stock.setInsideNum( stock.getInsideNum() - adjustStockReq.getAdjustNum() );

        int update = jdbcTemplate.update( "update aligo_flow_stock set inside_num = ? , update_time = ? where id = ?;",
                stock.getInsideNum(), new Date(), stock.getId() );

        Assert.isTrue(update > 0, "更新库存失败：" + adjustStockReq.getWhSn() + ":" + adjustStockReq.getSkuCode());
        System.out.println("AdjustInventoryProcessor 库存调整完毕...");

    }
}
