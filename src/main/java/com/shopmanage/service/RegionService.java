package com.shopmanage.service;

import com.shopmanage.entity.Region;

import java.util.List;
public interface RegionService {
    List<Region> selectAllRegionById(int id);
    List<Region> selectAllReginByParentId(int parent_id);
}
