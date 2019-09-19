package com.shopmanage.entity;

import lombok.Data;

import java.util.List;

@Data
public class BlPageInfo<T> extends BaseBean {
        private long total;
        private List<T> list;
        private int pages;
        private int pageNum;



}
