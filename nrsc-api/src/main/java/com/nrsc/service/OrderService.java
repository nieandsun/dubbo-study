package com.nrsc.service;

import com.nrsc.pojo.UserAddress;

import java.util.List;


public interface OrderService {

    /**
     * 初始化订单
     *
     * @param userId
     */
    List<UserAddress> initOrder(String userId);

}
