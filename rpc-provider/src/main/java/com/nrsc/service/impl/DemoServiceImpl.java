package com.nrsc.service.impl;

import com.alibaba.fastjson.JSON;
import com.nrsc.service.DemoService;
import java.util.Map;
public class DemoServiceImpl implements DemoService {
    @Override
    public Map<String, String> getHelloInfo(Map<String, String> info) {
        System.out.println("恭喜你， 调通了， 参数： " + JSON.toJSONString(info));
        info.put("msg", "你好， 调通了！ ");
        return info;
    }
}
