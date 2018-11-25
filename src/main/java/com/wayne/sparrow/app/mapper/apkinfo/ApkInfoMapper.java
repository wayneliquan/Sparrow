package com.wayne.sparrow.app.mapper.apkinfo;

import com.wayne.sparrow.app.pojo.ApkInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ApkInfoMapper {

    List<ApkInfo> listTargetApk();

    ApkInfo getApkInfoById(Long id);
}
