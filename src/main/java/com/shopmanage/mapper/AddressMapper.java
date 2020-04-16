package com.shopmanage.mapper;

import com.shopmanage.entity.Address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AddressMapper {

    @Insert("insert into address  (consignee,country_name,email,province_name,city_name,district_name,address,zipcode" +
            ",tel,mobile,default_address,country,province,district,city,uid) " +
            "VALUES (#{consignee},#{country_name},#{email},#{province_name},#{city_name},#{district_name},#{address},#{zipcode}," +
            "#{tel},#{mobile},#{default_address},#{country},#{province},#{district},#{city},#{uid})")
    int addAddress(Address address);

    @Delete("delete from address where id = #{id}")
    int deleteAddress(int id);

    @Update("update address set where uid =#{id}")
    int updateAddress(int id);

    @Select("select * from address where uid = #{id}")
    List<Address> selectAllAddress(int id);

    @Select("select * from address where id = #{id}")
    Address selectOneAddress(int id);
}
