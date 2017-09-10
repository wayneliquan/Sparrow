package com.wayne.sparrow.app.vo;

import com.wayne.sparrow.app.entity.system.SysResource;
import com.wayne.sparrow.app.entity.system.SysRole;
import com.wayne.sparrow.app.service.system.SysRoleService;
import com.wayne.sparrow.core.util.Validator;
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

    private static boolean isAuthorized(Long sysResourceId, List<Long> authorizedResourceIdList) {
        if (Validator.isNotNull(authorizedResourceIdList)) {
            return authorizedResourceIdList.contains(sysResourceId);
        } else {
            return false;
        }
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

    public static List<TreeNode> resourceToTree(List<SysResource> sysResourceList, List<Long> authorizedResourceIdList) {
        // 把resource 转化为tree
        Map<Long, List<SysResource>> tempMap = resourceToMap(sysResourceList);
        // pid = 0
        List<Long> keys = new ArrayList<>(tempMap.keySet());
        Collections.sort(keys);
        Long topId = keys.get(0);
        List<SysResource> topResourceList = tempMap.get(topId);

        List<TreeNode> topNodeList = new ArrayList<>();
        for(SysResource sysResource: topResourceList) {
            TreeNode treeNode = new TreeNode();
            if (isAuthorized(sysResource.getId(), authorizedResourceIdList)) {
                treeNode.toCheck();
                treeNode.toExpand();
            }
            tree(tempMap, treeNode, sysResource, authorizedResourceIdList);
            topNodeList.add(treeNode);
        }
        return topNodeList;
    }

    public static List<TreeNode> sysRoleToTree(List<SysRole> sysRoleList, List<Long> roleIdList) {
        List<TreeNode> topNodeList = new ArrayList<>();
        for (SysRole sysRole: sysRoleList) {
            TreeNode treeNode = new TreeNode();
            treeNode.setText(sysRole.getName());
            treeNode.setId(sysRole.getId());
            if (Validator.isNotNull(roleIdList)) {
                if (roleIdList.contains(sysRole.getId())) {
                    treeNode.toCheck();
                }
            }
            topNodeList.add(treeNode);
        }
        return topNodeList;
    }

    public static void tree(Map<Long, List<SysResource>> nodeMap, TreeNode treeNode, SysResource sysResource, List<Long> authorizedResourceIdList) {
        treeNode.setId(sysResource.getId());
        treeNode.setPid(sysResource.getPid());
        treeNode.setIcon(sysResource.getIcon());
        treeNode.setText(sysResource.getName());
        Long id = sysResource.getId();
        List<SysResource> tempList = nodeMap.getOrDefault(id, null);
        if (tempList != null) {
            for (SysResource subSysResource : tempList) {
                TreeNode subNode = new TreeNode();
                if (isAuthorized(subSysResource.getId(), authorizedResourceIdList)){
                    subNode.toCheck();
                }
                subNode.toExpand();
                tree(nodeMap, subNode, subSysResource, authorizedResourceIdList);
                treeNode.addSubTreeNode(subNode);
            }
        }
    }

}
