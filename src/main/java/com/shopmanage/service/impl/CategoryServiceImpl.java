package com.shopmanage.service.impl;

import com.shopmanage.entity.CategoryBean;
import com.shopmanage.mapper.CategoryMapper;
import com.shopmanage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public CategoryBean addCategoryBean(CategoryBean categoryBean) {
        return categoryMapper.addCategoryBean(categoryBean);
    }

    @Override
    public CategoryBean updateCategoryBean(CategoryBean categoryBean) {
        return categoryMapper.updateCategoryBean(categoryBean);
    }

    @Override
    public int deleteCategoryBean(String cid) {
        return categoryMapper.deleteCategoryBean(cid);
    }
}
