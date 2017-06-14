package service;

import dao.ItemDAO;

import java.util.ArrayList;

/**
 * Created by 是不是傻 on 2017/6/14.
 */
public class ItemList{
    private ArrayList<ItemDAO> itemDAOArrayList=new ArrayList<>();
    public void clear(){
        itemDAOArrayList.clear();
    }
    public void add(ItemDAO item){
        itemDAOArrayList.add(item);
    }
    public void delete(ItemDAO item){
        itemDAOArrayList.remove(item);
    }
    public ItemDAO get(int index){
        return itemDAOArrayList.get(index);
    }
    public ItemDAO get(String itemId){
        for(ItemDAO temp:itemDAOArrayList)
            if(temp.getId().equals(itemId))
                return temp;
        return null;
    }

    public String getItemsJson(){
        String jsonData="[";
        for(int i=0;i<itemDAOArrayList.size();i++){
            if(i<itemDAOArrayList.size()-1)
                jsonData+=itemDAOArrayList.get(i).getJson()+",";
            else
                jsonData+=itemDAOArrayList.get(i).getJson();
        }
        jsonData+="]";
        return jsonData;
    }
}
