package com.wayne.sparrow.core.common.bojo.area;

import java.util.List;

/**
 * 省，直辖市，自治区
 * 省包含多个市
 */
public class Province {
    Long id ;
    String code; // 行政代码
    int level; // 行政划分层级
    int sort; // 排序
    List<City> cities;
}
