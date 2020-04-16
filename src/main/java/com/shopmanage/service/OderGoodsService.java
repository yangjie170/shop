package com.shopmanage.service;

import com.shopmanage.entity.OderGoods;

import java.util.List;

public interface OderGoodsService {
    List<OderGoods> queryOderGoodsByOderState(String state);
    int addOderGoodsToOder(String order_id,OderGoods oderGoods);
}
