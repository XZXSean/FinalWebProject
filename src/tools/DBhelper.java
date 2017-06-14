package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBhelper {
    public static final String url = "jdbc:mysql://localhost:3306/wwt_db";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "wwt";
    public static final String password = "123456";

    public Connection conn = null;
    public PreparedStatement pst = null;

    public DBhelper(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
