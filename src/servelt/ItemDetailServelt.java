package servelt;

import dao.ItemDAO;
import service.ItemMap;
import service.ItemsMapController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 是不是傻 on 2017/6/14.
 * 这个servlet负责解决Item细节输出的问题
 */
@WebServlet(name = "ItemDetailServelt")
public class ItemDetailServelt extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        //得到搜索的内容
        String id = request.getParameter("id");

        ItemDAO item = ItemsMapController.getItemById(id);
        if (item == null)
            pw.print("{}");
        else
            pw.print(item.getDetailJson());

    }
}
