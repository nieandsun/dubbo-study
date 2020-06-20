package com.nrsc.service;

import com.nrsc.pojo.UserAddress;

import java.util.List;

public interface UserService {

    /***
     * 按照用户id返回用户所有的收货地址
     * @param userId
     * @return
     */
    List<UserAddress> getUserAddressList(String userId);
}
