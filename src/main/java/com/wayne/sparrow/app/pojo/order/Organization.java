package com.wayne.sparrow.app.pojo.order;

import lombok.Data;

import java.util.List;

@Data
public class Organization {

    long id;
    String orgName;
    boolean hasSub;
    List<Organization> subOrgList;
}
