package com.h2t.springboot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;


@SpringBootTest
class StoreApplicationTests {
    @Autowired
    private DataSource dataSource;

    @DisplayName("sayOK")
    @Test
    void contextLoads() {
        System.out.println("ok");
    }

    @DisplayName("测试数据库连接")
    @Test
    public void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
