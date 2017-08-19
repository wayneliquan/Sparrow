package com.wayne.sparrow.app.vo;

import com.wayne.sparrow.app.entity.system.SysResource;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanliquan on 17-8-17.
 * Description:
 */
@Data
public class SysResourceNode {
    private SysResource sysResource;
    private List<SysResourceNode> subSysResourceNodeList;

    public SysResourceNode(SysResource sysResource) {
        this.sysResource = sysResource;
    }

    public void addSubNode(SysResourceNode subNode) {
        if (subSysResourceNodeList == null) {
            subSysResourceNodeList = new ArrayList<>();
        }
        subSysResourceNodeList.add(subNode);
    }
}
