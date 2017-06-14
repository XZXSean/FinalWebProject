package dao;

import java.util.Objects;

/**
 * Created by 是不是傻 on 2017/6/14.
 */
public class ItemDAO {
    private String Id;
    private String department;
    private String description;
    private String name;
    private String date;
    private String address;
    private String comments;

    public ItemDAO(String id, String name, String department, String description) {
        Id = id;
        this.name = name;
        this.department = department;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return "{id: '" + this.getId() + "',name:'" + this.getName() + "',department: '" + this.getDepartment() + "',description: '" + this.getDescription() + "'}";
    }

    public String getDetailJson() {
        return "{id: '" + this.getId() + "',name: '" + this.getName() + "',date: '" + this.getDate() + "',address:'" + this.getAddress() + "',comments:'" + this.getComments() + "'}";
    }

    @Override
    public boolean equals(Object object) {
        boolean flag = object instanceof ItemDAO;
        if (flag == false) {
            return false;
        }
        ItemDAO item = (ItemDAO) object;
        if (this.getId().equals(item.getId()) && this.getDepartment().equals(item.getDepartment()) && this.getDescription().equals(item.getDescription()))
            return true;
        else
            return false;
    }
}
