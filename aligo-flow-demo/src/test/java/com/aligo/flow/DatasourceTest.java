package com.aligo.flow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据源连接测试
 *
 * @author minghui.y
 * @create 2022-01-29 9:22 上午
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatasourceTest {

    @Autowired
    private DataSource dataSource;


    @Test
    public void testConnect() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
