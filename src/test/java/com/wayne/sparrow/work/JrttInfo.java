package com.wayne.sparrow.work;

import lombok.Data;

@Data
public class JrttInfo {
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

}

