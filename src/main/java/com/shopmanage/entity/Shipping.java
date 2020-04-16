package com.shopmanage.entity;

public class Shipping {
    private int shipping;
    private String shippingName;
    private String fromat_shipping_fee;

    public Shipping() {
    }

    public Shipping(int shipping, String shippingName, String fromat_shipping_fee) {
        this.shipping = shipping;
        this.shippingName = shippingName;
        this.fromat_shipping_fee = fromat_shipping_fee;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getFromat_shipping_fee() {
        return fromat_shipping_fee;
    }

    public void setFromat_shipping_fee(String fromat_shipping_fee) {
        this.fromat_shipping_fee = fromat_shipping_fee;
    }
}
