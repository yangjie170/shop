package com.shopmanage.service;

import com.shopmanage.entity.CategoryBean;
import com.shopmanage.entity.ProductBean;

import java.util.List;

/**************************************************
 * copyright (c) 2020, www.winan.com.cn All Rights Reserved.
 * created by peng.cao
 * date:       2020/3/1 0001
 * since:      1.0.0.1
 * description:
 *
 **************************************************/
public interface CategoryService {

    List<CategoryBean> getAllCategory();

    CategoryBean addCategoryBean(CategoryBean categoryBean);


    CategoryBean updateCategoryBean(CategoryBean categoryBean);


    int deleteCategoryBean(String cid);

    List<ProductBean> getProductByCategory(int cid);
}
