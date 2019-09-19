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
    private String pdesc;
    private  Integer pflag;
    private  String detial;
    private  Integer cid;




}
