package com.shopmanage.entity.DTO;

import com.shopmanage.entity.CategoryBean;

import java.util.List;

public class CategoryPrimary extends CategoryBean {
    private List<CategoryBean> children;


    public List<CategoryBean> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryBean> children) {
        this.children = children;
    }
}
