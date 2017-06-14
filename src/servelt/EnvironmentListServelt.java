package servelt;

import dao.ItemDAO;
import service.ItemList;
import tools.DBhelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        String searchContent=request.getParameter("searchContent");

        //经过一系列的查找 doSomething()
        ItemList itemList=search("");

        //返回json数据
        System.out.println(itemList.getItemsJson());
        pw.print(itemList.getItemsJson());
    }

    private ItemList search(String key){
        String sql = "select * from environmentlist";//SQL语句
        DBhelper db1 = new DBhelper(sql);//创建DBHelper对象
        ItemList itemArrayList=new ItemList();
        try {
            ResultSet ret = db1.pst.executeQuery();//执行语句，得到结果集
            while (ret.next()) {
                ItemDAO item = new ItemDAO(ret.getString(1),ret.getString(2),ret.getString(3));
                itemArrayList.add(item);
            }//显示数据
            ret.close();
            db1.close();//关闭连接
            return itemArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return itemArrayList;
        }
    }


}
