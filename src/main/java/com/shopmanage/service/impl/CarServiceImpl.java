package com.shopmanage.service.impl;

import com.shopmanage.entity.CarGoods;
import com.shopmanage.mapper.CarMapper;
import com.shopmanage.service.CarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {


    @Autowired
    private CarMapper carMapper;

    @Override
    public int createCarGoods(CarGoods carGoods) {
        return carMapper.addCar(carGoods);
    }

    @Override
    public int deleteCargoods(int rec_id) {
        return carMapper.deleteCargoods(rec_id);
    }

    @Override
    public int deleteALlCarGoods(int uid) {
        return carMapper.deleteAllCarGoodsByUid(uid);
    }


    @Override
    public int updateCarGoods(int new_number,int rec_id,String subtotal) {
        return carMapper.updateCargoods(new_number,subtotal,rec_id);
    }

    @Override
    public List<CarGoods> selectAllCarGoods(int uid) {
        return carMapper.selectAllCarGoods(uid);
    }

    @Override
    public CarGoods selectOneRec(int rec_id) {
        return carMapper.selectOneRec(rec_id);
    }
}
