package com.shopmanage.service;

import com.shopmanage.entity.OderBean;
import com.shopmanage.entity.OrderNum;
import com.shopmanage.entity.OrderNumber;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

//订单功能接口
public interface OderService {
    //增删改查
    //增加一个订单
    int addOrder(OderBean oderBean);
    //删
    int deletOrder(String oid);
    //改
    int updateOrder(OderBean oderBean);
    //条件查
    List<OderBean> selectByMap(OderBean oderBean);
    //模糊标题查
    List<OderBean> selectLike(int s);
    //查询所有
    List<OderBean> selectAllOrder();

    OderBean editOrder(String oid);

    List<OderBean> queryOrderByUid(int uid);

    List<OrderNum> selectOrderNum(int uid);
}
