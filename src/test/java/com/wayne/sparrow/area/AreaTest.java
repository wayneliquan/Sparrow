package com.wayne.sparrow.area;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wayne.sparrow.core.common.bojo.area.Area;

import java.util.ArrayList;
import java.util.List;

public class AreaTest {

    public static void main(String[] args) {
        Area province = new Area();
        province.setId(1L);
        province.setName("福建省");

        List<Area> cityList = new ArrayList<>();
        Area city;
        for (int i = 1; i< 3 ; i ++) {
            city = new Area();
            city.setId(i*10+1L);
            city.setName("城市"+i);

            List<Area> countyList = new ArrayList<>();
            Area county;
            for (int j = 1; j< 3 ; j ++) {
                county = new Area();
                county.setId(j + i + 1L);
                county.setName("县" + j);
                countyList.add(county);
            }
            city.setSubArea(countyList);
            cityList.add(city);
        }
        province.setSubArea(cityList);

        System.out.println(JSON.toJSONString(province, SerializerFeature.NotWriteDefaultValue));
    }
}
