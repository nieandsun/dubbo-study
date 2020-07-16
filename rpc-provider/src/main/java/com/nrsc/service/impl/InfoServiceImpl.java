package com.nrsc.service.impl;

import com.nrsc.service.InfoService;

public class InfoServiceImpl implements InfoService {
    @Override
    public String sayHello(String content) {
        return "The Content is " + content;
    }
}
