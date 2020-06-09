package com.shopmanage.service.impl;

import com.shopmanage.entity.CategoryBean;
import com.shopmanage.entity.ProductBean;
import com.shopmanage.mapper.CategoryMapper;
import com.shopmanage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**************************************************
 * copyright (c) 2020, www.winan.com.cn All Rights Reserved.
 * created by peng.cao
 * date:       2020/3/1 0001
 * since:      1.0.0.1
 * description:
 *
 **************************************************/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryBean> getAllCategory() {
        return categoryMapper.getAllCategory();
    }

    @Override
    public Integer addCategoryBean(CategoryBean categoryBean) {
        categoryBean.setCid(UUID.randomUUID().toString());
        return categoryMapper.addCategoryBean(categoryBean);
    }

    @Override
    public Integer updateCategoryBean(CategoryBean categoryBean) {
        return categoryMapper.updateCategoryBean(categoryBean);
    }

    @Override
    public int deleteCategoryBean(String cid) {
        return categoryMapper.deleteCategoryBean(cid);
    }

    @Override
<<<<<<< HEAD
    public List<ProductBean> getProductByCategory(int cid) {
        return categoryMapper.getProductByCategory(cid);
    }

=======
    public List<CategoryBean> getCategoryByName(CategoryBean categoryBean) {
        return categoryMapper.getCategoryByName(categoryBean);
    }

    @Override
    public CategoryBean getCategory(String cid) {
        return categoryMapper.getCategory(cid);
    }
>>>>>>> 58f68ddbf6c10b69a1fe5db603d4565c08ff0594
}
