package dao;

import java.util.Objects;

/**
 * Created by 是不是傻 on 2017/6/14.
 */
public class ItemDAO {
    private String Id;
    private String department;
    private String description;

    public ItemDAO(String id, String department, String description) {
        Id = id;
        this.department = department;
        this.description = description;
    }

    public String getJson() {
        return "{id: '" + this.getId() + "',department: '" + this.getDepartment() + "',description: '" + this.getDescription() + "'}";
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
}
