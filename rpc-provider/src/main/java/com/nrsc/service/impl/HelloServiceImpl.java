package com.nrsc.service.impl;

import com.alibaba.fastjson.JSON;
import com.nrsc.service.HelloService;

import java.util.Map;

public class HelloServiceImpl implements HelloService {

    @Override
    public Map<String, String> getHelloInfo(Map<String, String> info) {
        System.out.println("恭喜你， 调通了， 参数： " + JSON.toJSONString(info));
        info.put("msg", "你好， 调通了！ ");
        return info;
    }
}
