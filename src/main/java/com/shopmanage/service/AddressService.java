package com.shopmanage.service;

import com.shopmanage.entity.Address;

import java.util.List;

public interface AddressService {
    int addAddress(Address address);
    int deleteAddress(int id);
    int updateAddress(int id);
    List<Address> selectAllAddress(int id);
    Address selectOneAddress(int id);
}
