package servelt;

import dao.ItemDAO;
import service.ItemsService;
import tools.JsonHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Shinelon on 2017/6/15.
 */
@WebServlet(name = "searchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        //得到搜索的内容
        String searchContent = request.getParameter("searchContent");
        //经过一系列的查找 doSomething()

        ArrayList<ItemDAO> items=ItemsService.getItemsByKey(searchContent);
        String itemsJson= JsonHelper.getItemsJson(items);
        //返回json id,name,department,description数据
        pw.print(itemsJson);
    }
}
