package com.wayne.sparrow.app.pojo;

import lombok.Data;

@Data
public class DeviceInfo {
    private String uuid;
    private String serialno;
    private String androidId;
    private String name;
    private String brand;
    private String model;
    private int sdk;
    private String release;
    private int widthPixels;
    private int heightPixels;
    private String status;
}
