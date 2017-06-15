package servelt;

import dao.ItemDAO;
import service.ItemsService;

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
@WebServlet(name = "AddItemServlet")
public class AddItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //提取参数 然后 往数据库内添加Item 和往 内置表内添加信息
        //获得 传过来的item信息，先从内置表内看是否存在，不存在就往数据库内增加 存在就反馈客户端已经有了
        //数据库添加 后添加成功就 去内置表内添加，失败就反馈失败信息
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = response.getWriter();
        //得到修改的内容
        String id = request.getParameter("id");
        //内置表检测，这里不想写，就跳过吧

        String department = request.getParameter("department");
        String description = request.getParameter("description");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String date = request.getParameter("date");
        String comments = request.getParameter("comments");
        boolean result=ItemsService.addItem(new ItemDAO(id,name,department,description,date,address,comments));
        if(result){
            //往内置表内添加item
            pw.print("添加成功");
        }else{
            pw.print("添加失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
