package com.wayne.sparrow.app.servlet.wechat;

import com.wayne.sparrow.app.po.wechat.TextMessage;
import com.wayne.sparrow.core.util.CheckUtil;
import com.wayne.sparrow.core.util.MessageUtils;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

@WebServlet(urlPatterns = "/weChatApi")
public class WeChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        PrintWriter out = resp.getWriter();
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.println(signature + "-" + timestamp);
            out.print(echostr);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            Map<String, String> map = MessageUtils.xmlToMap(req);
            String fromUserName = map.get("FromUserName");
            String toUserName = map.get("ToUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String msgId = map.get("MsgId");

            String message = null;
            if ("text".equals(msgType)) {
                TextMessage textMessage = new TextMessage();
                textMessage.setFromUserName(toUserName);
                textMessage.setToUserName(fromUserName);
                textMessage.setMsgType("text");
                textMessage.setContent("点击跳转：http://www.jingchengheyi.com");
                textMessage.setCreateTime(new Date().getTime());
                textMessage.setMsgId(msgId);
                message = MessageUtils.textMessageToXml(textMessage);
            }
            out.print(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
//        req.getRequestDispatcher("/weChat/text").forward(req, resp);
    }
}
