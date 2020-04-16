package com.shopmanage.service.impl;

import com.shopmanage.entity.OderGoods;
import com.shopmanage.mapper.OderGoodsMapper;
import com.shopmanage.service.OderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OderGoodsServiceImpl implements OderGoodsService {

    @Autowired
    private OderGoodsMapper oderGoodsMapper;
    @Override
    public List<OderGoods> queryOderGoodsByOderState(String state) {
        return oderGoodsMapper.selectOderGoodsByOderState(state);
    }

    @Override
    public int addOderGoodsToOder(String order_id, OderGoods oderGoods) {
        return oderGoodsMapper.addOderGoods(oderGoods,order_id);
    }
}
