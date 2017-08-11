package com.wayne.sparrow.mapper;

import com.wayne.sparrow.app.mapper.system.SysUserMapper;
import org.junit.Test;
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
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void testFindById() {
        System.out.println(sysUserMapper.findById(1L));
    }
}
