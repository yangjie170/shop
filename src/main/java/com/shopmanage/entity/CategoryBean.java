package com.shopmanage.entity;

import lombok.Data;

@Data
public class CategoryBean  extends BaseBean {

    private  String cid;
    private  String cname;



    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
