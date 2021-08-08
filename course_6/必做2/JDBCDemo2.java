package io.test.demo.demo.jdbcdemo;

import java.sql.*;

/**
 * PreparedStatement
 */
public class JDBCDemo2 {

    /**
     * 1000000跳数据大概215s
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            con.setAutoCommit(false);
            long start = System.currentTimeMillis();
            //插入
            statement = con.prepareStatement("INSERT INTO `tb_sys_user` (`username`, `pwd`, `realname`, `mobile`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (?, ?, ?, '18888888888', NOW(), '1', NOW(), '1')");

            for (int n = 0; n < 1000000; n++) {
                statement.setString(1, "un" + n);
                statement.setString(2, n + "");
                statement.setString(3, "rn" + n);
                statement.addBatch();
            }

            statement.executeBatch();

            con.commit();
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
