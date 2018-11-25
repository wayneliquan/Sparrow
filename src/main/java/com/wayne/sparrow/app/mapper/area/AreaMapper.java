package com.wayne.sparrow.app.mapper.area;

import com.wayne.sparrow.core.common.bojo.area.Area;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AreaMapper {
    Area findById(Long id);
    Area findTreeById(Long id);
}
