package com.wayne.sparrow;

import com.wayne.sparrow.app.mina.MinaSessionManager;
import com.wayne.sparrow.app.service.task.AutoReadTaskManager;
import com.wayne.sparrow.core.constants.SysConstants;
import com.wayne.sparrow.core.util.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationListener;

@ServletComponentScan
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
        SpringContextUtils.setApplicationContext(event.getApplicationContext());
        new SysConstants().init();
        MinaSessionManager minaSessionManager = SpringContextUtils.getBean(MinaSessionManager.class);
        AutoReadTaskManager taskManager = SpringContextUtils.getBean(AutoReadTaskManager.class);
        taskManager.init();
        System.out.println("start MINA Server");
        minaSessionManager.startService();
        System.out.println("end MINA Server");
    }
}