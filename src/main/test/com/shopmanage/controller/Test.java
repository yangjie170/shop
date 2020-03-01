package com.shopmanage.controller;

import com.shopmanage.entity.OderBean;
import com.shopmanage.service.OderService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private OderController oderController;
    @Autowired
    private OderService oderService;
    @org.junit.Test
    public void test(){
        System.out.println(oderService.selectAllOrder());
      //  System.out.println(oderService.selectLike("2"));
    }

    @org.junit.Test
    public void test1(){
        OderBean oderBean = new OderBean();
        oderBean.setUid("121");
        oderBean.setAddress("2");
        oderBean.setName("213");
        oderBean.setOid("21");
        oderBean.setState(1);
        oderBean.setOrdertime(new Date());
        oderBean.setTelephone("1231241");
        oderBean.setTotal(1231);
        System.out.println(oderService.addOrder(oderBean)==1?"yes":"no");
    }

    @org.junit.Test
    public void test2(){
        OderBean oderBean = new OderBean();
        List<OderBean> list = oderService.selectByMap(oderBean);
        for (OderBean o :list
                ) {
            System.out.println(o.getName()+o.getState());
        }
    }
    @org.junit.Test
    public void test3(){
        List<OderBean> list = oderService.selectLike(1);
        for (OderBean o : list
             ) {
            System.out.println(o.getTotal());
        }
    }

    @org.junit.Test
    public void test4(){
    }
}
