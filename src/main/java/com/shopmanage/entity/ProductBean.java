package com.shopmanage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Slf4j
@Data
public class ProductBean extends CategoryBean{
    private Integer pid;
    private String pname;
    private Integer markprice;
    private Integer shopprice;
    private String pimage;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private Date pdate;
    private Integer ishot;
    private Integer pdesc;
    private  Integer pflag;
    private  String detial;
    private  Integer cid;



    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getMarkprice() {
        return markprice;
    }

    public void setMarkprice(Integer markprice) {
        this.markprice = markprice;
    }

    public Integer getShopprice() {
        return shopprice;
    }

    public void setShopprice(Integer shopprice) {
        this.shopprice = shopprice;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public Integer getIshot() {
        return ishot;
    }

    public void setIshot(Integer ishot) {
        this.ishot = ishot;
    }

    public Integer getPdesc() {
        return pdesc;
    }

    public void setPdesc(Integer pdesc) {
        this.pdesc = pdesc;
    }

    public Integer getPflag() {
        return pflag;
    }

    public void setPflag(Integer pflag) {
        this.pflag = pflag;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    @Override
    public Integer getCid() {
        return cid;
    }

    @Override
    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
