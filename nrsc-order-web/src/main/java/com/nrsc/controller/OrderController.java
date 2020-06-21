package com.nrsc.controller;

import com.nrsc.pojo.UserAddress;
import com.nrsc.service.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @DubboReference
    private OrderService orderService;

    @RequestMapping("/initOrder/{uid}")
    public List<UserAddress> initOrder(@PathVariable("uid") String userId) {
        return orderService.initOrder(userId);
    }


}
