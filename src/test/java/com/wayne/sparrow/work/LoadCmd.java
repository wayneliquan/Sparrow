package com.wayne.sparrow.work;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadCmd {
    public static void main(String[] args) {
        String path = "cmd/今日头条/6.9.8/1080x1920.cmd";
        Properties p = getProperties(path);
        System.out.println(p.toString());
        System.out.println(p.getProperty("WHILE"));
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
}
