package com.nrsc.service.impl;

import com.alibaba.fastjson.JSON;
import com.nrsc.service.OrderService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

/***
 * 为简化实现， 继承UnicastRemoteObject 类
 */
public class OrderServiceImpl extends UnicastRemoteObject implements OrderService {

    /***必须要有一个无参构造，且抛出RemoteException异常*/
    public OrderServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public Map<String, String> getOrderInfo(Map<String, String> info) {
        System.out.println("恭喜你， 调通了， 参数： " + JSON.toJSONString(info));
        info.put("msg", "你好， 调通了！ ");
        return info;
    }
}

