package com.shopmanage.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
@Data
public class ResponseBean<T> {

    int code = 200;
    @JsonIgnore
    String message = "";
    @JsonIgnore
    String action = "";
    T data = null;

}
