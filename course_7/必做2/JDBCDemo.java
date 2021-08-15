package io.test.demo.demo.jdbcdemo;

import java.sql.*;

public class JDBCDemo {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3307/sharding_db?characterEncoding=utf8&serverTimezone=UTC";
        String username = "root";
        String password = "root";
        Connection con = null;
        Statement statement = null;
        try {
            long start = System.currentTimeMillis();
            con = DriverManager.getConnection(url, username, password);
            statement = con.createStatement();
            //插入
            for (int n = 0; n < 10; n++) {
                statement.executeUpdate("INSERT INTO `tb_sys_user` (`username`, `pwd`, `realname`, `mobile`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES ('" + "un" + n + "',' " + n +"',' " + "rn" + n + "', '18888888888', NOW(), '1', NOW(), '1')");
            }
            System.out.println((System.currentTimeMillis() - start)/1000 + "s");
        } catch (SQLException se) {
            System.out.println("数据库连接失败！");
            se.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

}
