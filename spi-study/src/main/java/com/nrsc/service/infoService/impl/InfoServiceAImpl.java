package com.nrsc.service.infoService.impl;

import com.nrsc.service.InfoService;

public class InfoServiceAImpl implements InfoService {
    @Override
    public Object sayHello(String name) {
        System.out.println(name + ",你好，调通了A实现！");
        return name + ",你好，调通了A实现！";
    }
}
