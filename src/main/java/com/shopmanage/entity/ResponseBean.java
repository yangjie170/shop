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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
