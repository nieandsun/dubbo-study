package com.nrsc.service.impl;

import com.nrsc.service.JdbcService;

public class JdbcServiceAImpl implements JdbcService {
    @Override
    public int insert(String name) {
        System.out.println(name + ",你好，调通了A实现！");
        return 1;
    }
}
