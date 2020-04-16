package com.shopmanage.entity.DTO;

import com.shopmanage.entity.Picture;
import com.shopmanage.entity.ProductBean;

import java.util.List;

public class ProductDTO extends ProductBean {
    private Picture picture;
    private List<Picture> pictures;

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public void initProduct(ProductBean productBean){
        this.setCid(productBean.getCid());
        this.setPimage(productBean.getPimage());
        this.setDetial(productBean.getDetial());
        this.setIshot(productBean.getIshot());
        this.setMarkprice(productBean.getMarkprice());
        this.setPdate(productBean.getPdate());
        this.setPdesc(productBean.getPdesc());
        this.setPflag(productBean.getPflag());
        this.setPid(productBean.getPid());
        this.setPname(productBean.getPname());
        this.setShopprice(productBean.getShopprice());
        this.setCname(productBean.getCname());
    }
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
