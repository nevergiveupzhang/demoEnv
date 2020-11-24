package com.example.demo.springDemo.bean.copy;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BeanUtilDemo {
    public static void main(String[] args) {
        List<UserPO> pos = new ArrayList<>();
        pos.add(new UserPO(1,"zhangsan"));
        pos.add(new UserPO(2,"lisi"));

        List<UserVO> vos = new ArrayList<>();
        for(UserPO po : pos){
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(po,vo);
            vos.add(vo);
        }

        System.out.println(vos);
    }
}
