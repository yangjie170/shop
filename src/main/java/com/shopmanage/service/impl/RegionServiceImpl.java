package com.shopmanage.service.impl;

import com.shopmanage.entity.Region;
import com.shopmanage.mapper.RegionMapper;
import com.shopmanage.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Region> selectAllRegionById(int id) {
        return regionMapper.selectAllRegionById(id);
    }

    @Override
    public List<Region> selectAllReginByParentId(int parent_id) {
        return regionMapper.selcetAllByParentId(parent_id);
    }
}
