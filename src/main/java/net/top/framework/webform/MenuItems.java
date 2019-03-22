package net.top.framework.webform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Project:net-top-framwork-webapp
 * @Package net.top.framework.webform
 * @Description:
 * @author: xsy
 * @date： 2016/12/27
 * @version： V1.0
 */
public class MenuItems {
    private Integer id;
    private String name;
    private String icon;
    private List<Map> items = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Map> getItems() {
        return items;
    }

    public void setItems(List<Map> items) {
        this.items = items;
    }

    public void addItem(Map map) {
        items.add(map);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
