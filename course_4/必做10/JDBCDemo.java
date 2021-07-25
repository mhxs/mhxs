package io.test.demo.demo;

import java.sql.*;

public class JDBCDemo {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        Connection con = null;
        Statement statement = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            statement = con.createStatement();
            //插入
            statement.executeUpdate("INSERT INTO tb_stu_info (`name`, `age`) VALUES ('王二', '11')");

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
