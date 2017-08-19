package com.wayne.sparrow.app.vo;

import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.entity.system.SysRole;
import com.wayne.sparrow.app.service.system.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 *
 */
@Component
public class EntityToVO {

    @Autowired
    private SysRoleService roleService;

    private static EntityToVO entityToVO;

    @PostConstruct
    public void init() {
        entityToVO = this;
        entityToVO.roleService = this.roleService;
    }

    public static List<MenuNode> resourceToMenuNode(List<SysResource> sysResourceList) {
        Map<Long, List<SysResource>> tempMap = resourceToMap(sysResourceList);
        List<Long> keys = new ArrayList<>(tempMap.keySet());
        Collections.sort(keys);
        Long topId = keys.get(0);
        System.out.println("topId = " +  topId);
        List<SysResource> topResourceList = tempMap.get(topId);

        List<MenuNode> topNodeList = new ArrayList<>();
        for(SysResource sysResource: topResourceList) {
            MenuNode subNode = new MenuNode();
            treeMenu(tempMap, subNode, sysResource);
            topNodeList.add(subNode);
        }
        return topNodeList;
    }

    public static void treeMenu(Map<Long, List<SysResource>> nodeMap, MenuNode node, SysResource sysResource) {
        node.setId(sysResource.getId());
        node.setPid(sysResource.getPid());
        node.setIcon(sysResource.getIcon());
        node.setHref(sysResource.getUrl());
        node.setText(sysResource.getName());
        Long id = sysResource.getId();
        List<SysResource> tempList = nodeMap.getOrDefault(id, null);
        if (tempList != null) {
            node.setHasSub(true);
            for (SysResource subSysResource : tempList) {
                MenuNode subNode = new MenuNode();
                treeMenu(nodeMap, subNode, subSysResource);
                node.addSubMenuNode(subNode);
            }
        }
    }

    public static List<SysResourceNode> resourceToNode(List<SysResource> sysResourceList) {
        Map<Long, List<SysResource>> tempMap = resourceToMap(sysResourceList);
        List<Long> keys = new ArrayList<>(tempMap.keySet());
        Collections.sort(keys);
        Long topId = keys.get(0);
        System.out.println("topId = " +  topId);
        List<SysResource> topResourceList = tempMap.get(topId);

        assert (topResourceList!=null && !topResourceList.isEmpty());
        List<SysResourceNode> topNodeList = new ArrayList<>();
        for(SysResource sysResource: topResourceList) {
            SysResourceNode node = new SysResourceNode(sysResource);
            treeResource(tempMap, node, sysResource);
            topNodeList.add(node);
        }
        return topNodeList;
    }

    public static void treeResource(Map<Long, List<SysResource>> nodeMap, SysResourceNode node, SysResource sysResource) {
        Long topId = sysResource.getId();
        List<SysResource> tempList = nodeMap.getOrDefault(topId, null);
        if (tempList != null) {
            for (SysResource subSysResource : tempList) {
                SysResourceNode subNode = new SysResourceNode(subSysResource);
                treeResource(nodeMap, subNode, subSysResource);
                node.addSubNode(subNode);
            }
        }
    }

    public static Map<Long, List<SysResource>> resourceToMap(List<SysResource> sysResourceList) {
        Map<Long, List<SysResource>> tempMap = new HashMap<>();
        for (SysResource sysResource : sysResourceList) {
            List<SysResource> tempResourceList = tempMap.getOrDefault(sysResource.getPid(),null);
            if (tempResourceList == null) {
                tempResourceList = new ArrayList<>();
                tempMap.put(sysResource.getPid(), tempResourceList);
            }
            tempResourceList.add(sysResource);
        }
        return tempMap;
    }

    public static List<TreeNode> resourceToTree(List<SysResource> sysResourceList) {
        // 把resource 转化为tree
        Map<Long, List<SysResource>> tempMap = resourceToMap(sysResourceList);
        // pid = 0
        List<Long> keys = new ArrayList<>(tempMap.keySet());
        Collections.sort(keys);
        Long topId = keys.get(0);
        System.out.println("topId = " +  topId);
        List<SysResource> topResourceList = tempMap.get(topId);

        assert (topResourceList!=null && !topResourceList.isEmpty());
        List<TreeNode> topNodeList = new ArrayList<>();
        for(SysResource sysResource: topResourceList) {
            TreeNode treeNode = new TreeNode();
            tree(tempMap, treeNode, sysResource);
            topNodeList.add(treeNode);
        }
        return topNodeList;
    }

    public static List<TreeNode> sysRoleToTree(List<SysRole> sysRoleList) {
        List<TreeNode> topNodeList = new ArrayList<>();
        for (SysRole sysRole: sysRoleList) {
            TreeNode treeNode = new TreeNode();
            treeNode.setText(sysRole.getName());
            treeNode.setId(sysRole.getId());
            topNodeList.add(treeNode);
        }
        return topNodeList;
    }

    public static void tree(Map<Long, List<SysResource>> nodeMap, TreeNode treeNode, SysResource sysResource) {
        treeNode.setId(sysResource.getId());
        treeNode.setPid(sysResource.getPid());
        treeNode.setIcon(sysResource.getIcon());
        treeNode.setText(sysResource.getName());
        Long id = sysResource.getId();
        List<SysResource> tempList = nodeMap.getOrDefault(id, null);
        if (tempList != null) {
            for (SysResource subSysResource : tempList) {
                TreeNode subNode = new TreeNode();
                tree(nodeMap, subNode, subSysResource);
                treeNode.addSubTreeNode(subNode);
            }
        }
    }

}
