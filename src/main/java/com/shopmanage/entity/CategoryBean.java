package com.shopmanage.entity;

import lombok.Data;

@Data
public class CategoryBean  extends BaseBean {

    private  Integer cid;
    private  String cname;



    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
