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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shinelon on 2017/6/15.
 */
@WebServlet(name = "ModifyServlet")
public class ModifyItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得 传过来的item信息，往数据库内增加，成功则再内置表内添加 该item
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        //得到修改的内容
        String id = request.getParameter("id");
        String department = request.getParameter("department");
        String description = request.getParameter("description");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String date = request.getParameter("date");
        String comments = request.getParameter("comments");
        Map<String,String> data=new HashMap<>();
        data.put("department",department);
        data.put("description",description);
        data.put("name",name);
        data.put("address",address);
        data.put("date",date);
        data.put("comments",comments);

        boolean result=ItemsService.modifyItem(id,data);
        if(result){
            //需要往内置表内 添加修改的Item 或修改内置表内对应Item
            pw.print("修改成功");
        }else
            pw.print("修改失败");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
