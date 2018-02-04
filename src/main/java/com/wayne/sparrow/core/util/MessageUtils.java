package com.wayne.sparrow.core.util;

import com.thoughtworks.xstream.XStream;
import com.wayne.sparrow.app.po.wechat.TextMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将xml格式，转为Map
 */
public class MessageUtils {
    public static Map<String, String> xmlToMap(HttpServletRequest req) throws IOException, DocumentException{
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream ins = req.getInputStream();
        Document doc = reader.read(ins);
        Element root = doc.getRootElement();

        List<Element> list = root.elements();
        for (Element e: list) {
            map.put(e.getName(), e.getText());
        }
        ins.close();
        return map;
    }

    public static String textMessageToXml(TextMessage textMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }
}
