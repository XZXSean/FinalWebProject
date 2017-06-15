package service;

import dao.ItemDAO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shinelon on 2017/6/15.
 * 服务器中用于管理哪些数据被删除或者被修改的
 */

public class ItemsMap {
    private Map<String, ItemWithStatus> itemsWithStatus=new HashMap<>();
    public void add(ItemDAO item){
        if(item!=null){
            itemsWithStatus.put(item.getId(),new ItemWithStatus(item,ItemWithStatus.ADD_ITEM));
        }
    }

    public void delete(ItemDAO item){
        if(item!=null){
            itemsWithStatus.put(item.getId(),new ItemWithStatus(item,ItemWithStatus.DELETE_ITEM));
        }
    }

    public void modify(ItemDAO item){
        if(item!=null){
            itemsWithStatus.put(item.getId(),new ItemWithStatus(item,ItemWithStatus.MODIFY_ITEM));
        }
    }

    class ItemWithStatus{
        private ItemDAO item;
        private int status;
        public static final int DELETE_ITEM=-1;
        public static final int MODIFY_ITEM=0;
        public static final int ADD_ITEM=1;
        public ItemWithStatus(ItemDAO item, int status) {
            this.item = item;
            this.status = status;
        }

    }
}
