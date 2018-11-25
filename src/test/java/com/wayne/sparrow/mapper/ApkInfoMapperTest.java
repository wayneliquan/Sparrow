package com.wayne.sparrow.mapper;

import com.wayne.sparrow.app.mapper.apkinfo.ApkInfoMapper;
import com.wayne.sparrow.app.mapper.autoread.AutoReadScriptMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApkInfoMapperTest {
    @Autowired
    private ApkInfoMapper mapper;

    @Test
    public void testFindById() {
        log.error("come on");
        System.out.println(mapper.getApkInfoById(1L));
    }
}
