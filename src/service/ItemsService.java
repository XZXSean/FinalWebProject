package service;

import dao.ItemDAO;
import tools.DBhelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Shinelon on 2017/6/15.
 * 用于从数据库提取数据 并返回对象
 */
public class ItemsService {

    //获得Item
    public static ItemDAO getItemById(String id) {
        ItemDAO item = null;
        String sql = "select id,name,department,description,date,address,comments from environmentlist where id='" + id + "'";//SQL语句
        DBhelper db1 = new DBhelper(sql);//创建DBHelper对象
        try {
            ResultSet ret = db1.pst.executeQuery();//执行语句，得到结果集
            if (ret.next())
                item = new ItemDAO(ret.getString(1), ret.getString(2), ret.getString(3),
                        ret.getString(4), ret.getString(5), ret.getString(6), ret.getString(7));
            ret.close();
            db1.close();//关闭连接
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    //删除对应id的item
    public static boolean deleteItems(String[] ids){
        //删除item  直接数据库内删除，删除成功的话 就从内置表里面 添加删除item信息 然后返回删除成功   删除失败就直接返回删除失败
        String sql = "delete from environmentlist where ";
        for (int i = 0; i < ids.length-1 ; i++)
            sql+="id='"+ids[i]+"' or ";
        sql+="id='"+ids[ids.length-1]+"'";

        DBhelper db1 = new DBhelper(sql);//创建DBHelper对象
        boolean flag = false;
        try {
            flag = db1.pst.executeUpdate() > 0;
            db1.close();//关闭连接
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            return flag;
        }
    }

    //获得符合查询条件的Item的列表
    public static ArrayList<ItemDAO> getItemsByKey(String key) {
        ArrayList<ItemDAO> items = new ArrayList<>();
        String sql = analysisKey(key);
        DBhelper db1 = new DBhelper(sql);//创建DBHelper对象
        try {
            ResultSet ret = db1.pst.executeQuery();//执行语句，得到结果集
            while (ret.next())
                items.add(new ItemDAO(ret.getString(1), ret.getString(2), ret.getString(3),
                        ret.getString(4), ret.getString(5), ret.getString(6), ret.getString(7)));
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    //添加Item
    public static boolean addItem(ItemDAO item) {
        String sql = "insert into environmentlist values('"
                + item.getId() + "','"
                + item.getDepartment() + "','"
                + item.getDescription() + "','"
                + item.getName() + "','"
                + item.getDate() + "','"
                + item.getAddress() + "','"
                + item.getComments() + "')";

        DBhelper db1 = new DBhelper(sql);//创建DBHelper对象
        boolean flag = false;
        try {
            flag = db1.pst.executeUpdate() == 1;
            db1.close();//关闭连接
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            return flag;
        }
    }

    //修改Item数据 将map key对应的字段修改为value
    public static boolean modifyItem(String id, Map<String, String> data) {
        String sql = "update environmentlist set ";
        Iterator<String> keySet = data.keySet().iterator();
        while (keySet.hasNext()) {
            String key = keySet.next();
            if (keySet.hasNext())
                sql += key + "= '" + data.get(key) + "',";
            else {
                sql += key + "= '" + data.get(key) + "' where id='" + id + "'";
                break;
            }
        }
        DBhelper dBhelper = new DBhelper(sql);
        boolean flag = false;
        try {
            flag = dBhelper.pst.executeUpdate() == 1;
            dBhelper.close();//关闭连接
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            return flag;
        }
    }

    //将关键字解析成为sql语句
    private static String analysisKey(String key) {
        //关键字包括 id,名字，department,description,date,address,comments 7种  我选择搜索前4种  后面的 拒绝搜索
        //需要用到数据库的正则表达式
        //select * from table where id 包含key or department包含key or description 包含key 或者 name 包含 key
        String sql = "select id,name,department,description,date,address,comments from environmentlist where id like '%"
                +key+"%' or name like '%"+key+"%' or department like '%"+key+"%' or description like '%"+key+"%'";
        return sql;
    }
}
