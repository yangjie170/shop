package com.shopmanage.entity.DTO;

import com.shopmanage.entity.OrderNumber;
import com.shopmanage.entity.UserBean;

public class UserDTO{

    private Integer id;
    private String name;
    private String rank_name;
    private int rank_level;
    private OrderNumber order_num;

    public UserDTO(UserBean user,OrderNumber oderNum) {
        this.id=user.getUid();
        this.name=user.getName();
        this.rank_level=2;
        this.rank_name=user.getUsername();
        this.order_num = oderNum;
    }

    public OrderNumber getOder_num() {
        return order_num;
    }

    public void setOder_num(OrderNumber oder_num) {
        this.order_num = oder_num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderNumber getOrder_num() {
        return order_num;
    }

    public void setOrder_num(OrderNumber order_num) {
        this.order_num = order_num;
    }

    public String getRank_name() {
        return rank_name;
    }

    public void setRank_name(String rank_name) {
        this.rank_name = rank_name;
    }

    public int getRank_level() {
        return rank_level;
    }

    public void setRank_level(int rank_level) {
        this.rank_level = rank_level;
    }


}
