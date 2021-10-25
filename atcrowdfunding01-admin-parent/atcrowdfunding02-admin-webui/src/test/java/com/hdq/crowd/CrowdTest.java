package com.hdq.crowd;

import com.hdq.crowd.entity.Admin;
import com.hdq.crowd.mapper.AdminMapper;
import com.hdq.crowd.service.api.AdminService;
import com.hdq.crowd.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author hdq
 * @create 2021-10-17 20:59
 */
// 这里用junit5进行测试
@SpringJUnitConfig(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Test
    public void testService(){
        adminService.saveAdmin(new Admin(null,"hdq","123123","hdq","rui@qq.com",null));
    }

    @Test
    public void  testMapper(){
        Admin admin = new Admin(null,"Rachel","123123","rui","rui@qq.com",null);
        System.out.println(adminMapper.insert(admin));
    }
    @Test
    public void  testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
