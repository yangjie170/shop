package com.shopmanage.mapper;

import com.shopmanage.entity.CarGoods;
import com.shopmanage.entity.CartGoods;
import com.shopmanage.entity.ProductBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CarMapper {

    @Insert("insert into car values (#{uid},#{rec_id},#{goods_id},#{goods_name},#{goods_number},#{subtotal},#{img})")
    int addCar(CarGoods carGoods);

    @Delete("delete from car where rec_id = #{rec_id}")
    int deleteCargoods(int rec_id);

    @Update("update car set goods_number =#{new_number},subtotal=#{subtotal} where rec_id =#{rec_id}")
    int updateCargoods(int new_number,String subtotal,int rec_id);

    @Results({
            @Result(property = "uid",column = "uid"),
            @Result(property = "rec_id",column = "rec_id",id = true),
            @Result(property = "goods_id",column = "goods_id"),
            @Result(property = "goods_name",column = "goods_name"),
            @Result(property = "goods_number",column = "goods_number"),
            @Result(property = "subtotal",column = "subtotal"),
            @Result(property = "img",column = "img")
    })
    @Select("select * from car where uid =#{uid}")
    List<CarGoods> selectAllCarGoods(Integer uid);

    @Results({
            @Result(property = "uid",column = "uid"),
            @Result(property = "rec_id",column = "rec_id",id = true),
            @Result(property = "goods_id",column = "goods_id"),
            @Result(property = "goods_name",column = "goods_name"),
            @Result(property = "goods_number",column = "goods_number"),
            @Result(property = "subtotal",column = "subtotal"),
            @Result(property = "img",column = "img")
    })
    @Select("select * from car where rec_id = #{rec_id}")
    CarGoods selectOneRec(int rec_id);

    @Delete("Delete from car where uid = #{uid}")
    int deleteAllCarGoodsByUid(int uid);
}
