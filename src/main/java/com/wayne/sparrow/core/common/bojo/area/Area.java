package com.wayne.sparrow.core.common.bojo.area;

import lombok.Data;

import java.util.List;

@Data
public class Area {
    Long id ;
    String name;
    String code; // 行政代码
//    int level; // 行政划分层级
//    int sort; // 排序
    Long pid;
    List<Area> subArea;
}
