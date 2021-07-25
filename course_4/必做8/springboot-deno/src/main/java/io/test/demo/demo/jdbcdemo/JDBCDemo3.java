package io.test.demo.demo.jdbcdemo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.Properties;

/**
 * PreparedStatement
 */
public class JDBCDemo3 {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&serverTimezone=UTC";
        String username = "root";
        String password = "123456";

        // 加载属性文件并解析：
        Properties props = new Properties();
        props.setProperty("jdbcUrl",url);
        props.setProperty("driverClassName","com.mysql.cj.jdbc.Driver");
        props.setProperty("dataSource.user",username);
        props.setProperty("dataSource.password",password);
        props.setProperty("dataSource.databaseName","test");
        props.setProperty("dataSource.maximumPoolSize","10");
        HikariConfig config = new HikariConfig(props);
        HikariDataSource sHikariDataSource = new HikariDataSource(config);

        for (int i = 0;i < 20;i++){
            doImport(sHikariDataSource);
        }

        sHikariDataSource.close();
    }

    private static void doImport(HikariDataSource sHikariDataSource) throws SQLException {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = sHikariDataSource.getConnection();
            con.setAutoCommit(false);
            //批量插入
            statement = con.prepareStatement("INSERT INTO tb_stu_info (`name`, `age`) VALUES (?, ?)");

            for (int n = 0; n < 100; n++) {
                statement.setString(1, "zb" + n);
                statement.setInt(2, n);
                statement.addBatch();
            }

            statement.executeBatch();

            //记录id
            Long id = null;

            //查询
            ResultSet rs = statement.executeQuery("SELECT * FROM tb_stu_info ORDER BY id DESC LIMIT 1");
            while (rs.next()) {
                id = rs.getLong("id");
                System.out.println(id + "-" + rs.getString("name")
                        + "-" + rs.getInt("age"));
            }

            //修改
            statement.executeUpdate("UPDATE tb_stu_info SET age = 14 WHERE id = " + id);

            //删除
            statement.executeUpdate("DELETE FROM tb_stu_info WHERE id = " + id);

            con.commit();

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
