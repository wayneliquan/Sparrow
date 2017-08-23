package com.wayne.sparrow;

import com.wayne.sparrow.core.constants.SysConstants;
import com.wayne.sparrow.core.util.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class SparrowApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SparrowApplication.class);
        application.addListeners(new AppStartListener());
        application.run(args);
    }
}

class AppStartListener implements ApplicationListener<ApplicationReadyEvent> {
    /**
     * spring boot 加载完成后的回调，这里初始化系统数据。
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        SpringContextUtil.setApplicationContext(event.getApplicationContext());
        new SysConstants().init();
    }
}