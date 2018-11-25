package com.wayne.sparrow.work;

import com.alibaba.fastjson.JSON;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 今日头条
 maxX=1080
 minY=300
 maxY=1600
 count=5
 START_CMD=am start -n com.ss.android.article.news/.activity.MainActivity -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
 PRE_CMD=input tap 400 280
 BianLiYeMian={"y": 400, "step":3, "stepLen": 300}
 REFRESH_CMD=input swipe 300 500 300 1000 300
 READ_CMD=input swipe 500 1300 500 600 200
 BACK_CMD=input tap 60 150
 */
public class JrttWorker extends Worker {
    public volatile static boolean isRun = false;
    private int maxX=1080;
    private int minY=300;
    private int maxY=1600;
    private int count=5;
    private String startCmd="am start -n com.ss.android.article.news/.activity.MainActivity -a android.intent.action.MAIN -c android.intent.category.LAUNCHER";
    private String preCmd="input tap 400 280";
    private BianLiYeMian bianLiYeMian;
    private String refreshCmd="input swipe 300 500 300 1000 300";
    private String readCmd="input swipe 500 1300 500 600 200";
    private String backCmd="input tap 60 150";
    private Properties properties;

    public void init(Properties properties) {
        this.properties = properties;
        String bstr = properties.getProperty("BianLiYeMian");
        bianLiYeMian = JSON.parseObject(bstr, BianLiYeMian.class);
    }

    @Override
    public void run() {
        // 开始读的遍历
        try {
            isRun = true;
            Thread.sleep(5000);
            traverseScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
        isRun = false;
    }

    public void traverseScreen() throws Exception {
        Log.d("wayne", "遍历页面");
        for (int i = 0; i < bianLiYeMian.getCount(); i++) {
            if (!isRun) {
                break;
            }
            // input tap 250 280
            int y = bianLiYeMian.getY() + i * bianLiYeMian.getStepLen();
            String cmd = "input tap 500 " + y ;
            Log.d("wayne", cmd);
            // 点击页面
//            MyService.rootCommand(cmd);
            Thread.sleep(5000);

            // 等10是 开始模拟阅读
            virtualRead();
        }
    }

    /**
     * 模拟阅读
     */
    public void virtualRead() throws Exception {
        // 每10 秒向上滑动一次。
        int i = 5;
        while (i-- > 0) {
//            MyService.rootCommand(CmdConsts.滑动);
            System.out.println(readCmd);
        }
        goBack();
    }

    public void goBack() throws Exception {
//        MyService.rootCommand(CmdConsts.退回);
        System.out.println(backCmd);
        Thread.sleep(2000);
    }

    public static void main(String[] args) {
        String path = "cmd/今日头条/6.9.8/1080x1920.cmd";
        Properties p = getProperties(path);
        JrttWorker worker = new JrttWorker();
        worker.init(p);
        Thread thread = new Thread(worker);
        thread.start();
    }

    public static Properties getProperties(String path) {
        Properties properties = new Properties();
        try {
            FileInputStream stream = new FileInputStream(path);
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Override
    public void shutdown() {
        isRun = false;
    }

}