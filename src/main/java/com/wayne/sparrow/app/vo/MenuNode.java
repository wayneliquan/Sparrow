package com.wayne.sparrow.app.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanliquan on 17-8-18.
 * Description:
 */
@Data
public class MenuNode {
    private Long id;
    private Long pid;
    private String text;
    private String href;
    private Boolean hasSub;
    private String icon;
    private List<MenuNode> nodes;

    public void addSubMenuNode(MenuNode subNode) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }
        nodes.add(subNode);
    }
}
