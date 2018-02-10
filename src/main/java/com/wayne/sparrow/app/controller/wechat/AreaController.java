package com.wayne.sparrow.app.controller.wechat;

import com.wayne.sparrow.core.common.bojo.area.Area;
import com.wayne.sparrow.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/area")
public class AreaController extends BaseController{

    @RequestMapping("/demo")
    public String test() {
        return "area/demo";
    }

    @RequestMapping("/getById")
    @ResponseBody
    public Area getById(@RequestParam(name = "id", defaultValue = "0") Long id) {
        Area p = new Area();
        p.setId(id);
        p.setName("Area"+id); //1, 2
        List<Area> subArea = new ArrayList<>();
        p.setSubArea(subArea);

        for (int i = 1; i < 3; i++) {
            Area c = new Area();
            Long cid = 10 * id + i; //11,12 ; 21,22
            c.setId(cid);
            c.setName("Area" + c.getId());
            subArea.add(c);
            List<Area> xArea = new ArrayList<>();
            c.setSubArea(xArea);

//            for (int j = 1; j < 3; j++) {
//                Area x = new Area();
//                x.setId(100 * id + 10 * i + j); // 111,112, 121,122
//                x.setName("县" + x.getId());
//                xArea.add(x);
//            }
        }
        return p;
    }
}
