package com.wayne.sparrow.app.mapper.autoread;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AutoReadScriptMapper {
    String getScript(Long apkInfoId, int widthPixels, int heightPixels);
}
