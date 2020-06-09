package com.shopmanage.entity;

public class Filter {
    public static final String SORT_PRICE_DESC = "price_desc"; // 价格由高到低
    public static final String SORT_PRICE_ASC = "price_asc"; // 价格由低到高
    public static final String SORT_IS_HOT = "is_hot"; // 热度

    private String keywords="";
    private int category_id;
    private String sort_by=SORT_IS_HOT;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getSort_by() {
        return sort_by;
    }

    public void setSort_by(String sort_by) {
        this.sort_by = sort_by;
    }
}
