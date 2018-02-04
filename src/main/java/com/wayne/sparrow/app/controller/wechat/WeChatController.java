package com.wayne.sparrow.app.controller.wechat;

import com.wayne.sparrow.app.po.wechat.TextMessage;
import com.wayne.sparrow.core.common.controller.BaseController;
import com.wayne.sparrow.core.util.MessageUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequestMapping("/weChat")
public class WeChatController extends BaseController {

    @RequestMapping("/text")
    @ResponseBody
    public void text(HttpServletRequest req) {
        TextMessage textMessage = new TextMessage();
        textMessage.setContent("您发送的消息是：来家里");
        textMessage.setMsgType("text");
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setFromUserName("gh_cd3083df45d4");
        textMessage.setToUserName("oBb0_0ohcqJICl0b8lV");
        String message = MessageUtils.textMessageToXml(textMessage);
        System.out.println(message);
    }


}
