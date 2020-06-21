package com.nrsc.service.impl;

import com.nrsc.pojo.UserAddress;
import com.nrsc.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
@DubboService
public class UserServiceImpl implements UserService {

    @Override
    public List<UserAddress> getUserAddressList(String userId) {

        UserAddress address1 = new UserAddress(1, "北京XXX",
                "1", "李老师", "010-5555555", "Y");

        UserAddress address2 = new UserAddress(2, "山东XXXX",
                "1", "王老师", "0531-6666666", "N");
        return Arrays.asList(address1, address2);

    }
}
