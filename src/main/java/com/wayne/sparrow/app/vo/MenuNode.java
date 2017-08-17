package com.wayne.sparrow.app.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wayne on 2017/8/17.
 */
@Data
public class MenuNode {
    private String name;
    List<MenuNode> subMenuList;

    public MenuNode() {}

    public MenuNode(String name) {
        this.name = name;
    }

    public void addSubMenu(MenuNode node) {
        if (subMenuList == null) {
            subMenuList = new ArrayList<>();
        }
        subMenuList.add(node);
    }
}
