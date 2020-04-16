package com.shopmanage.mapper;

import com.shopmanage.entity.OderGoods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OderGoodsMapper {
    @Results(value = {
            @Result(property = "goods_id",column = "pid"),
            @Result(property = "name",column = "name"),
            @Result(property = "goods_number",column = "count"),
            @Result(property = "subtotal",column = "subtotal"),
            @Result(property = "formated_shop_price",column = "total"),
    })
    @Select("select * from orderitem,orders where orderitem.oid = orders.oid and orderitem.oid = #{oder_id}")
    List<OderGoods> selectOderGoodsByOderState(String state);
    @Insert("")
    int addOderGoods(OderGoods oderGoods, String id);
}
