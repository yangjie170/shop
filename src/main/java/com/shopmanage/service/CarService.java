package com.shopmanage.service;


import com.shopmanage.entity.CarGoods;

import java.util.List;

public interface CarService {
    int createCarGoods(CarGoods carGoods);
    int deleteCargoods(int rec_id);
    int updateCarGoods(int new_number,int rec_id,String subtotal);
    List<CarGoods> selectAllCarGoods(int uid);
    CarGoods selectOneRec(int rec_id);
}
