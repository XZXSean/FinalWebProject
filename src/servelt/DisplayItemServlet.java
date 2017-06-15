package servelt;

import service.ItemsService;
import tools.JsonHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shinelon on 2017/6/15.
 */
@WebServlet(name = "DisplayServlet")
public class DisplayItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //展示信息  传过来id，然后先从内置表内查找这个item的状态，删除的话  就返回已经删除
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        //得到修改的内容
        String id = request.getParameter("id");
        String jsonData=JsonHelper.getItemJson(ItemsService.getItemById(id));
//        System.out.println(jsonData);
        pw.print(jsonData);
    }
}
