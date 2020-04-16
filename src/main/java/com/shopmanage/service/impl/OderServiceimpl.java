package com.shopmanage.service.impl;

import com.shopmanage.entity.OderBean;
import com.shopmanage.mapper.OderMapper;
import com.shopmanage.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OderServiceimpl implements OderService {

    @Autowired
    private OderMapper oderMapper;

    @Override
    public int addOrder(OderBean oderBean) {

        return oderMapper.insertOrder(oderBean);
    }

    @Override
    public int deletOrder(int oid) {
        return oderMapper.deleteOrder(oid);
    }

    @Override
    public int updateOrder(OderBean oderBean) {
        return oderMapper.updateOrder(oderBean);
    }

    @Override
    public List<OderBean> selectByMap(OderBean oderBean) {
        return oderMapper.selectByMap(oderBean);
    }

    @Override
    public List<OderBean> selectLike(int s) {
        return oderMapper.selectLikeContent(s);
    }

    @Override
    public List<OderBean> selectAllOrder() {
        return oderMapper.selectAllOrder();
    }

    @Override
    public OderBean editOrder(String oid) {
        return oderMapper.editOrder(oid);
    }

    @Override
    public List<OderBean> queryOrderByUid(int uid) {
        return oderMapper.selectByUid(uid);
    }
}
