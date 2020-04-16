package com.shopmanage.entity;

public class Payment {
    private int pay_id;
    private String pay_name;
    private  String format_pay_fee;
    private String pay_code;

    public Payment() {
    }

    public Payment(int pay_id, String pay_name, String format_pay_fee, String pay_code) {
        this.pay_id = pay_id;
        this.pay_name = pay_name;
        this.format_pay_fee = format_pay_fee;
        this.pay_code = pay_code;
    }

    public int getPay_id() {
        return pay_id;
    }

    public void setPay_id(int pay_id) {
        this.pay_id = pay_id;
    }

    public String getPay_name() {
        return pay_name;
    }

    public void setPay_name(String pay_name) {
        this.pay_name = pay_name;
    }

    public String getFormat_pay_fee() {
        return format_pay_fee;
    }

    public void setFormat_pay_fee(String format_pay_fee) {
        this.format_pay_fee = format_pay_fee;
    }

    public String getPay_code() {
        return pay_code;
    }

    public void setPay_code(String pay_code) {
        this.pay_code = pay_code;
    }
}
