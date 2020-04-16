package com.shopmanage.mapper;

import com.shopmanage.entity.Region;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RegionMapper {

    @Results({
            @Result(property = "id", column = "region_id"),
            @Result(property = "parent_id", column = "parent_id"),
            @Result(property = "name", column = "region_name")
    })
    @Select("select * from region where region_id = #{id}")
    List<Region> selectAllRegionById(int id);

    @Results({
            @Result(property = "id", column = "region_id"),
            @Result(property = "parent_id", column = "parent_id"),
            @Result(property = "name", column = "region_name")
    })
    @Select("select * from region where parent_id = #{parent_id}")
    List<Region> selcetAllByParentId(int parent_id);


}
