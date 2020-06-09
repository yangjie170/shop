package com.shopmanage.entity;

import lombok.Data;

@Data
public class CategoryBean  extends BaseBean {

    private  String cid;
    private  String cname;

<<<<<<< HEAD
    public Integer getCid() {
=======


    public String getCid() {
>>>>>>> 58f68ddbf6c10b69a1fe5db603d4565c08ff0594
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
