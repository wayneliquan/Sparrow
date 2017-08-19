package com.wayne.sparrow.app.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanliquan on 17-8-15.
 * Description:
 */
@Data
public class TreeNode {
    private Long id;
    private Long pid;
    private String text;
    private String icon;//: "glyphicon glyphicon-stop",
    private String selectedIcon; //: "glyphicon glyphicon-stop",
    private String color; //: "#000000",
    private String backColor; //: "#FFFFFF",
    private String href; //: "#node-1",
    private Boolean selectable; //: true,
    private State state;
    private List<String> tags;// ['available'],
    private List<TreeNode> nodes;

    class State {
        private boolean checked;
        private boolean expanded;
        private boolean disabled;
        private boolean selected;
    }

    public void addSubTreeNode(TreeNode node) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }
        nodes.add(node);
    }
}
