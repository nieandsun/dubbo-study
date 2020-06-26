package com.nrsc.service.impl;

import com.nrsc.service.JdbcService;

public class JdbcServiceCImpl implements JdbcService {


    @Override
    public int insert(String name) {
        System.out.println(name + ",你好，调通了C实现！");
        return 1;
    }

}
