package com.shopmanage.mapper;

import com.shopmanage.entity.CategoryBean;
import com.shopmanage.entity.ProductBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**************************************************
 * copyright (c) 2020, www.winan.com.cn All Rights Reserved.
 * created by peng.cao
 * date:       2020/3/1 0001
 * since:      1.0.0.1
 * description:
 *
 **************************************************/
public interface CategoryMapper {


    @Select("select *  from category")
    List<CategoryBean> getAllCategory();

    @Insert("insert into category(cid,cname) values(#{cid},#{cname})")
    Integer addCategoryBean(CategoryBean categoryBean);


    @Update("update category set cname=#{cname} where cid=#{cid}")
    Integer updateCategoryBean(CategoryBean categoryBean);

    @Delete("delete from category where cid=#{cid}")
    int deleteCategoryBean(String cid);

<<<<<<< HEAD
    @Select("select * from product where cid=#{cid}")
    List<ProductBean> getProductByCategory(int cid);

=======
    @Select("select *  from category where cname=#{cname}")
    List<CategoryBean> getCategoryByName(CategoryBean categoryBean);

    @Select("select *  from category where cid=#{cid}")
    CategoryBean getCategory(String cid);
>>>>>>> 58f68ddbf6c10b69a1fe5db603d4565c08ff0594
}
