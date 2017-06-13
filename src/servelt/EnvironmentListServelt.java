package servelt;

import java.io.IOException;
import java.io.PrintWriter;

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

        //返回json数据
        String jsonData="[";
        jsonData+="{id: 'H009', department: '宫殿遗址', description: '绿化覆盖率低'},";
        jsonData+="{id: 'H010', department: '宫殿遗址', description: '绿化覆盖率低'},";
        jsonData+="{id: 'H011', department: '宫殿遗址', description: '绿化覆盖率低'},";
        jsonData+="{id: 'H012', department: '宫殿遗址', description: '绿化覆盖率低'},";
        jsonData+="{id: 'H013', department: '宫殿遗址', description: '绿化覆盖率低'}]";
        pw.print(jsonData);
    }
}
