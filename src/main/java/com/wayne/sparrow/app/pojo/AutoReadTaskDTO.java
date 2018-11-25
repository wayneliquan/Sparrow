package com.wayne.sparrow.app.pojo;

import lombok.Data;

import java.util.List;

@Data
public class AutoReadTaskDTO {
    public List<Long> apkInfoIds;
    public List<String> deviceInfoUuids;
}
