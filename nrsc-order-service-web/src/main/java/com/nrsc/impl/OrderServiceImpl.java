package com.nrsc.impl;

import com.nrsc.pojo.UserAddress;
import com.nrsc.service.OrderService;
import com.nrsc.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DubboService //暴漏dubbo服务
public class OrderServiceImpl implements OrderService {

    @DubboReference //消费服务
    private UserService userService;

    @Override
    public List<UserAddress> initOrder(String userId) {
        System.out.println("用户id：" + userId);
        //1、查询用户的收货地址
        List<UserAddress> addressList = userService.getUserAddressList(userId);
        return addressList;
    }
}
