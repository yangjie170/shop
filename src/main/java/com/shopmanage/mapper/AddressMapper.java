package com.shopmanage.mapper;

import com.shopmanage.entity.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AddressMapper {

    @Insert("insert into address  (consignee,country_name,email,province_name,city_name,district_name,address,zipcode" +
            ",tel,mobile,default_address,country,province,district,city,uid) " +
            "VALUES (#{consignee},#{country_name},#{email},#{province_name},#{city_name},#{district_name},#{address},#{zipcode}," +
            "#{tel},#{mobile},#{default_address},#{country},#{province},#{district},#{city},#{uid})")
    int addAddress(Address address);

    @Delete("delete from  address where id = #{id}")
    int deleteAddress(int id);

    @Update("update address set consignee=#{consignee},email=#{email},province_name=#{province_name}" +
            ",city_name=#{city_name},address=#{address},zipcode=#{zipcode},tel=#{tel},mobile=#{mobile}" +
            ",country=#{country},province=#{province},district=#{district},city=#{city} where uid =#{uid}")
    int updateAddress(Address address);

    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "consignee",column = "consignee"),
            @Result(property = "email",column = "email"),
            @Result(property = "province_name",column = "province_name"),
            @Result(property = "city_name",column = "city_name"),
            @Result(property = "address",column = "address"),
            @Result(property = "zipcode",column = "zipcode"),
            @Result(property = "tel",column = "tel"),
            @Result(property = "mobile",column = "mobile"),
            @Result(property = "country",column = "country"),
            @Result(property = "province",column = "province"),
            @Result(property = "district",column = "district"),
            @Result(property = "city",column = "city"),
            @Result(property = "district_name",column = "district_name")
    })
    @Select("select * from address where uid = #{id}")
    List<Address> selectAllAddress(int id);

    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "consignee",column = "consignee"),
            @Result(property = "email",column = "email"),
            @Result(property = "province_name",column = "province_name"),
            @Result(property = "city_name",column = "city_name"),
            @Result(property = "address",column = "address"),
            @Result(property = "zipcode",column = "zipcode"),
            @Result(property = "tel",column = "tel"),
            @Result(property = "mobile",column = "mobile"),
            @Result(property = "country",column = "country"),
            @Result(property = "province",column = "province"),
            @Result(property = "district",column = "district"),
            @Result(property = "city",column = "city"),
            @Result(property = "district_name",column = "district_name")
    })
    @Select("select * from address where uid = #{uid} and default_address = 1")
    List<Address> selectOneAddress(int uid);

    @Select("select * from address where id = #{id}")
    Address selectOneAddressBySid(int sid);
}
