package com.wayne.sparrow.mapper;

import com.wayne.sparrow.app.mapper.system.SysUserMapper;
import org.junit.Test;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhanliquan on 17-8-11.
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void testFindById() {
        log.error("come on");
        System.out.println(sysUserMapper.findById(1L));
    }
}
