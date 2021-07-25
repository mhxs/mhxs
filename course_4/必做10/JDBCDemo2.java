package io.test.demo.demo;

import java.sql.*;

/**
 * PreparedStatement
 */
public class JDBCDemo2 {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            con.setAutoCommit(false);
            //插入
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
