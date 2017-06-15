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

/**
 * Created by Shinelon on 2017/6/15.
 */
@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        //得到id数组
        String[] ids = request.getParameterValues("ids");
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
        if(ItemsService.deleteItems(ids))
            pw.print("删除成功");
        else
            pw.print("删除失败");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
