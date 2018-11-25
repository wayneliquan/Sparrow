package com.wayne.sparrow.app.controller.autoread;

import com.wayne.sparrow.app.mapper.apkinfo.ApkInfoMapper;
import com.wayne.sparrow.app.mapper.autoread.AutoReadScriptMapper;
import com.wayne.sparrow.app.mina.MinaSessionManager;
import com.wayne.sparrow.app.pojo.ApkInfo;
import com.wayne.sparrow.app.pojo.AutoReadTask;
import com.wayne.sparrow.app.pojo.AutoReadTaskDTO;
import com.wayne.sparrow.app.pojo.DeviceInfo;
import com.wayne.sparrow.app.service.task.AutoReadTaskManager;
import com.wayne.sparrow.core.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/autoread")
public class AutoReadController extends BaseController {
    @Autowired
    public ApkInfoMapper apkInfoMapper;

    @Autowired
    public AutoReadScriptMapper scriptMapper;

    @Autowired
    public MinaSessionManager minaSessionManager;

    @Autowired
    public AutoReadTaskManager taskManager;

    @Override
    public String getModulePath() {
        return "autoread/autoread";
    }

    @GetMapping("/inputTask")
    public String list(Model model) {
        // 列出所有apk
        List<ApkInfo> apkInfoList = apkInfoMapper.listTargetApk();
        // 获取当前的设备
        List<DeviceInfo> deviceInfoList = minaSessionManager.getCurrDeviceInfoList();

        model.addAttribute("apkInfoList", apkInfoList);
        model.addAttribute("deviceInfoList", deviceInfoList);
        return getModulePath() + "-inputTask";
    }

    @PostMapping("/startTask")
    @ResponseBody
    public String startTask(AutoReadTaskDTO taskDTO) {
        List<Long> appInfoIds = taskDTO.getApkInfoIds();
        List<String> deviceInfoUuid = taskDTO.getDeviceInfoUuids();

        for(String uuid : deviceInfoUuid) {
            DeviceInfo deviceInfo = minaSessionManager.getDeviceInfoByUuid(uuid);
            for (Long id : appInfoIds) {
                ApkInfo apkInfo = apkInfoMapper.getApkInfoById(id);
                String script = scriptMapper.getScript(apkInfo.getApkInfoId(),
                        deviceInfo.getWidthPixels(), deviceInfo.getHeightPixels());
                AutoReadTask autoReadTask = new AutoReadTask(deviceInfo, apkInfo, script);
                taskManager.addTask(autoReadTask);
            }
        }
        return "操作成功";
    }
}
