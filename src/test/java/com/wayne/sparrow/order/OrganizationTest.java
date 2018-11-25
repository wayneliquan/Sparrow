package com.wayne.sparrow.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wayne.sparrow.app.pojo.order.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationTest {
    public static void main(String[] args) {
        Organization org = new Organization();

        org.setId(304L);
        org.setOrgName("福建省分行");
        List<Organization> subOrgList = new ArrayList<>();
        org.setSubOrgList(subOrgList);

        Organization subOrg;

        subOrg = new Organization();
        subOrg.setId(111607);
        subOrg.setHasSub(false);
        subOrg.setOrgName("福建省分行计划财务部");
        subOrgList.add(subOrg);

        subOrg = new Organization();
        subOrgList.add(subOrg);
        subOrg.setId(111673);
        subOrg.setOrgName("三明市分行");
        subOrg.setHasSub(true);
        List<Organization> subSubOrgList = new ArrayList<>();
        subOrg.setSubOrgList(subSubOrgList);

        Organization subSubOrg;

        subSubOrg = new Organization();
        subSubOrgList.add(subSubOrg);
        subSubOrg.setId(110800);
        subSubOrg.setOrgName("尤溪县支行");

        subSubOrg = new Organization();
        subSubOrgList.add(subSubOrg);
        subOrg.setId(110799);
        subOrg.setOrgName("大田县支行");

        System.out.println(JSON.toJSONString(org));

        List<Organization> relevantDeptList = new ArrayList<>();
        org = new Organization();
        org.setId(112891);
        org.setOrgName("福建省分行办公室（党委办公室）");
        relevantDeptList.add(org);

        org = new Organization();
        org.setId(112892);
        org.setOrgName("福建省分行人力资源部（党委组织部）");
        relevantDeptList.add(org);

        org = new Organization();
        org.setId(112893);
        org.setOrgName("福建省分行个人金融部");
        relevantDeptList.add(org);

        System.out.println(JSON.toJSONString(relevantDeptList, SerializerFeature.NotWriteDefaultValue));
    }
}
