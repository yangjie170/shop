package com.shopmanage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shopmanage.entity.Address;
import com.shopmanage.entity.Response;
import com.shopmanage.entity.Session;
import com.shopmanage.entity.Status;
import com.shopmanage.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/address")
@Transactional
public class AddressController {
    @Autowired
    private AddressService addressService;
    @RequestMapping("/add")
    @ResponseBody
    public RspAdd addAdderss(@RequestParam Map<String,String> map){

        JSONObject jsonObject = JSON.parseObject(map.get("json"));
        Session session = jsonObject.getObject("session",Session.class);
        Address address = jsonObject.getObject("address",Address.class);

        RspAdd rspAdd = new RspAdd();
        address.setUid(session.getUid());
        address.setCountry_name("中国");
        addressService.addAddress(address);
        rspAdd.setStatus(new Status(1,200,"请求成功"));
        return rspAdd;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RspAdd deleteAddress(@RequestParam Map<String,String> map){
        JSONObject jsonObject = JSON.parseObject(map.get("json"));
        int id = jsonObject.getIntValue("address_id");
        addressService.deleteAddress(id);
        RspAdd rspAdd = new RspAdd();
        rspAdd.setStatus(new Status(1,200,"请求成功"));
        return rspAdd;
    }

    @RequestMapping("/update")
    @ResponseBody
    public RspAdd updateAddress(@RequestParam Map<String,String>map){

        JSONObject jsonObject = JSON.parseObject(map.get("json"));
        Session session = jsonObject.getObject("session", Session.class);
        Address address = jsonObject.getObject("address",Address.class);
        address.setUid(session.getUid());
        addressService.updateAddress(address);
        RspAdd rspAdd = new RspAdd();
        rspAdd.setStatus(new Status(1,200,"请求成功"));
        return rspAdd;
    }

    @RequestMapping("/list")
    @ResponseBody
    public RspList listAddress(@RequestParam Map<String,String> map){
        JSONObject jsonObject = JSON.parseObject(map.get("json"));
        Session session = jsonObject.getObject("session", Session.class);
        List<Address> list = addressService.selectAllAddress(session.getUid());
        RspList rspList = new RspList();
        rspList.setData(list);
        rspList.setStatus(new Status(1,200,"请求成功"));
        return rspList;
    }

    @RequestMapping("/info")
    @ResponseBody
    public RspGetOne addressInfo(@RequestParam Map<String,String> map){

        JSONObject jsonObject = JSONObject.parseObject(map.get("json"));
        int id = jsonObject.getIntValue("address_id");
        //List<Address> address =addressService.selectOneAddress(id);
        Address address =  addressService.selectOneAddressBySid(id);
        RspGetOne rspGetOne = new RspGetOne();
        rspGetOne.setData(address);
        rspGetOne.setStatus(new Status(1,200,"请求成功"));
        return rspGetOne;
    }
    @RequestMapping("/setDefault")
    @ResponseBody
    public RspAdd setDefault(@RequestParam Map<String,String> map){
        JSONObject jsonObject = JSONObject.parseObject(map.get("json"));
        int id = jsonObject.getIntValue("address_id");
        Address address = addressService.selectOneAddressBySid(id);
        address.setDefault_address(1);
        addressService.updateAddress(address);
        RspAdd rspAdd = new RspAdd();
        rspAdd.setStatus(new Status(1,200,"请求成功"));
        return rspAdd;
    }


    public static class RspAdd extends Response{

    }

    public static class RspList extends Response{
        List<Address> data;

        public List<Address> getData() {
            return data;
        }

        public void setData(List<Address> data) {
            this.data = data;
        }
    }
    public static class RspGetOne extends Response{
        Address data;

        public Address getData() {
            return data;
        }

        public void setData(Address data) {
            this.data = data;
        }
    }
}
