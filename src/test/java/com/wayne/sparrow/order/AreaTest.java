package com.wayne.sparrow.order;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AreaTest {

    public static void main(String[] args) {
        List<Area> list = new ArrayList<>();
        for (int k=1; k<3;k++) {
            Area p = new Area();
            p.setId(k);
            p.setName("省"+k);
            List<Area> subArea = new ArrayList<>();
            p.setSubArea(subArea);

            for (int i = 1; i < 3; i++) {
                Area c = new Area();
                c.setId(10 + i);
                c.setName("市" + c.getId());
                subArea.add(c);
                List<Area> xArea = new ArrayList<>();
                c.setSubArea(xArea);

                for (int j = 1; j < 3; j++) {
                    Area x = new Area();
                    x.setId(100 * j);
                    x.setName("县" + x.getId());
                    xArea.add(x);
                }
            }
            list.add(p);
        }
        System.out.println(JSON.toJSONString(list));
        System.out.println("{"+StringUtils.strip(list.toString(), "[]")+"}");
    }
}

@Data
class Area{
    int id;
    String name;
    List<Area> subArea;

    @Override
    public String toString() {
        String str;
        if (subArea != null && !subArea.isEmpty()) {
            String subAreaJson = subArea.toString();
            subAreaJson = subAreaJson.substring(1, subAreaJson.length()-1);
            str = "\"" + this.id  +"\": {\"name\":\"" + name + "\", \"subArea\":{" + subAreaJson + "}}";
        } else {
            str = "\"" + this.id  +"\": {\"name\":\"" + name + "\"}";
        }
        return str;
    }
}
