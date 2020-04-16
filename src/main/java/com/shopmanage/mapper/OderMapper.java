package com.shopmanage.mapper;

import com.shopmanage.entity.OderBean;

import java.util.List;
import java.util.Map;

//订单接口dao
public interface OderMapper {
    //增删改查
    //增加一个订单
    int insertOrder(OderBean oderBean);

    //删除一个订单
    int deleteOrder(int oid);

    //修改一个订单
    int updateOrder(OderBean oderBean);

    //按条件查询订单
    List<OderBean> selectByMap(OderBean oderBean);

    //模糊查询订单
    List<OderBean> selectLikeContent(int s);

    //查询所有订单
    List<OderBean> selectAllOrder();

    OderBean editOrder(String oid);

    List<OderBean> selectByUid(int uid);
}
