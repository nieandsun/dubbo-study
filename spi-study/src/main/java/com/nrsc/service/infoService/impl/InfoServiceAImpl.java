package com.nrsc.service.infoService.impl;

import com.nrsc.service.InfoService;
import org.apache.dubbo.common.URL;

//@Adaptive
public class InfoServiceAImpl implements InfoService {
    @Override
    public Object sayHello(String name) {
        System.out.println(name + ",你好，调通了A实现！");
        return name + ",你好，调通了A实现！";
    }
    @Override
    public Object passInfo(String msg, URL url) {
        System.out.println("恭喜你，调通了A实现:" + url);
        return msg;
    }
}
