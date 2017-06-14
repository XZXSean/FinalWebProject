package service;

import dao.ItemDAO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 是不是傻 on 2017/6/14.
 */
public class ItemMap {
    private Map<String, ItemDAO> itemsMap=new HashMap<>();

    public void addItem(ItemDAO item){
        if(item==null)
            return ;
        if(itemsMap.containsKey(item.getId()))
            return ;
        else
            itemsMap.put(item.getId(),item);
    }

    public void deleteItem(ItemDAO item){
        if(item==null)
            return ;
        if(itemsMap.containsKey(item.getId()))
            itemsMap.remove(item.getId());
    }

    public ItemDAO getItem(String key){
        return itemsMap.get(key);
    }

    public void clear(){
        itemsMap.clear();
    }

    public String getItemsJson(){
//        for (String key : itemsMap.keySet()) {
//            System.out.println("Key = " + key);
//        }
        String jsonData="[";
        //遍历map所有Item
        for (ItemDAO item : itemsMap.values())
            jsonData+=item.getJson()+",";
        jsonData=jsonData.substring(0,jsonData.length()-1);
        jsonData+="]";
        return jsonData;
    }

    public String getItemDetailJson(String key){
        ItemDAO item=getItem(key);
        if(item==null)
            return "{}";
        else
            return item.getDetailJson();
    }
}
