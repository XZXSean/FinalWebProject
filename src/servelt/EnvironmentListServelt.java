package servelt;

import dao.ItemDAO;
import service.ItemMap;
import tools.DBhelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Shinelon on 2017/6/13.
 */
public class EnvironmentListServelt extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        //得到搜索的内容
        String searchContent = request.getParameter("searchContent");

        //经过一系列的查找 doSomething()
        ItemMap itemMap = search("");

        //返回json数据
        pw.print(itemMap.getItemsJson());
    }

    private ItemMap search(String key) {
        String sql = "select id,name,department,description from environmentlist";//SQL语句
        DBhelper db1 = new DBhelper(sql);//创建DBHelper对象
        ItemMap itemMap=new ItemMap();
        try {
            ResultSet ret = db1.pst.executeQuery();//执行语句，得到结果集
            while (ret.next()) {
                ItemDAO item = new ItemDAO(ret.getString(1), ret.getString(2), ret.getString(3), ret.getString(4));
                itemMap.addItem(item);
            }//显示数据
            ret.close();
            db1.close();//关闭连接
            return itemMap;
        } catch (SQLException e) {
            e.printStackTrace();
            return itemMap;
        }
    }


}
