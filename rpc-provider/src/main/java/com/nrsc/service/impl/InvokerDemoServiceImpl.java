package com.nrsc.service.impl;

import com.nrsc.service.InvokerDemoService;

public class InvokerDemoServiceImpl implements InvokerDemoService {

    private String name;

    public InvokerDemoServiceImpl(String name) {
        this.name = name;
    }

    @Override
    public String sayHello(String msg) {
        System.out.println("hello " + name + ", msg:" + msg);
        return "Hello, " + name + "," + msg;
    }
}
