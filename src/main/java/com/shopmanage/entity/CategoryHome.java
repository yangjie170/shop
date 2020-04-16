package com.shopmanage.entity;

import com.shopmanage.entity.DTO.ProductDTO;

import java.util.List;

public class CategoryHome extends CategoryBean {
    private List<ProductDTO> goods;

    public List<ProductDTO> getGoods() {
        return goods;
    }

    public void setGoods(List<ProductDTO> goods) {
        this.goods = goods;
    }
}
