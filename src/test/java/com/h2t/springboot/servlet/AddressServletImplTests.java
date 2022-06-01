package com.h2t.springboot.servlet;

import com.h2t.springboot.entity.Address;
import com.h2t.springboot.service.exception.ServiceException;
import com.h2t.springboot.service.implement.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressServletImplTests {

    @Autowired
    private AddressServiceImpl addressServletImpl;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("123231232");
        address.setName("老婆");
        addressServletImpl.addNewAdddress(2,"管理员",address);
    }

    @Test
    public void setDefault() {
        try {
            Integer aid = 12;
            Integer uid = 2;
            String username = "系统管理员";
            addressServletImpl.setDefault(aid, uid, username);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getByAid() {
        try {
            Integer aid = 19;
            Integer uid = 1;
            Address address = addressServletImpl.getByAid(aid, uid);
            System.out.println(address);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
