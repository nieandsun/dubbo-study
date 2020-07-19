/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nrsc.service.impl;

import com.alibaba.fastjson.JSON;
import com.nrsc.service.DemoService;

import java.util.Map;

public class ZkDemoServiceImpl implements DemoService {

    @Override
    public Map<String, String> getHelloInfo(Map<String, String> info) {
        System.out.println("恭喜你， 调通了ZkDemoServiceImpl，参数： " + JSON.toJSONString(info));
        info.put("msg", "你好， 调通了！ ");
        return info;
    }
}