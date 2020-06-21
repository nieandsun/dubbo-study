package com.nrsc.controller;

import com.nrsc.pojo.UserAddress;
import com.nrsc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    /***
     * 在将web和service合到一个项目后，这里就可以直接将Service注入了
     */
    @Autowired
    private OrderService orderService;

    @RequestMapping("/initOrder/{uid}")
    public List<UserAddress> initOrder(@PathVariable("uid") String userId) {
        return orderService.initOrder(userId);
    }


}
