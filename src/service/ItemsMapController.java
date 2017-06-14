package service;

import dao.ItemDAO;
import tools.DBhelper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 是不是傻 on 2017/6/14.
 */
public class ItemsMapController {
    public static ItemMap itemMap=new ItemMap();

    //从数据库中读取到itemMap里面去，如果修改的话先从这里面修改，再写入数据库
    public static void search(String key) {
        String sql = "select id,name,department,description,date,address,comments from environmentlist";//SQL语句
        DBhelper db1 = new DBhelper(sql);//创建DBHelper对象
        try {
            ResultSet ret = db1.pst.executeQuery();//执行语句，得到结果集
            while (ret.next())
                itemMap.addItem(ret.getString(1), ret.getString(2), ret.getString(3), ret.getString(4),ret.getString(5),ret.getString(6),ret.getString(7));
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //对key对应的item进行修改，修改对应的属性
    public static void modify(String key,String attr,String value){

    }

    public static ItemDAO getItemById(String id){
        return itemMap.getItem(id);
    }
}
