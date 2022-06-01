package com.h2t.springboot.servlet;


import com.h2t.springboot.entity.Order;
import com.h2t.springboot.service.IOrderService;
import com.h2t.springboot.service.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;

    @Test
    public void create() {
        try {
            Integer aid = 20;
            Integer[] cids = { 5, 6};
            Integer uid = 16;
            String username = "订单管理员";
            Order order = orderService.create(aid, cids, uid, username);
            System.err.println(order);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
