package com.h2t.springboot.mapper;

import com.h2t.springboot.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(1);
        address.setPhone("123231232");
        address.setName("老婆");
        Integer insert = addressMapper.insert(address);
        System.out.println(insert);
    }
    @Test
    public void countByuid(){
        Integer integer = addressMapper.countByuid(1);
        System.out.println(integer);
    }

    @Test
    public void findByUid() {
        Integer uid = 16;
        List<Address> list = addressMapper.findByUid(uid);
        System.out.println("count=" + list.size());
        for (Address item : list) {
            System.out.println(item);
        }
    }
    @Test
    public void findByAid() {
        Integer aid = 19;
        Address result = addressMapper.findByAid(aid);
        System.out.println(result);
    }

    @Test
    public void updateNonDefaultByUid() {
        Integer uid = 30;
        Integer rows = addressMapper.updateNonDefaultByUid(uid);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateDefaultByAid() {
        Integer aid = 19;
        String modifiedUser = "管理员";
        Date modifiedTime = new Date();
        Integer rows = addressMapper.updateDefaultByAid(aid, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }
}
