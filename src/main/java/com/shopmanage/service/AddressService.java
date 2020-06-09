package com.shopmanage.service;

import com.shopmanage.entity.Address;

import java.util.List;

public interface AddressService {
    int addAddress(Address address);
    int deleteAddress(int id);
    int updateAddress(Address address);
    List<Address> selectAllAddress(int id);
    List<Address> selectOneAddress(int uid);
    Address selectOneAddressBySid(int sid);
}
