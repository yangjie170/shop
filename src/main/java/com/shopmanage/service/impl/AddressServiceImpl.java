package com.shopmanage.service.impl;

import com.shopmanage.entity.Address;
import com.shopmanage.mapper.AddressMapper;
import com.shopmanage.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public int addAddress(Address address) {
        return addressMapper.addAddress(address);
    }

    @Override
    public int deleteAddress(int id) {
        return addressMapper.deleteAddress(id);
    }

    @Override
    public int updateAddress(Address address) {
        return addressMapper.updateAddress(address);
    }

    @Override
    public List<Address> selectAllAddress(int id) {
        return addressMapper.selectAllAddress(id);
    }

    @Override
    public List<Address> selectOneAddress(int uid) {
        return addressMapper.selectOneAddress(uid);
    }

    @Override
    public Address selectOneAddressBySid(int sid) {
        return addressMapper.selectOneAddressBySid(sid);
    }
}
