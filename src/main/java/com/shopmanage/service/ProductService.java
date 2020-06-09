package com.shopmanage.service;


import com.github.pagehelper.PageInfo;
import com.shopmanage.entity.ProductBean;

import java.util.List;

public interface ProductService {
  List<ProductBean> queryProductByterm( String pname,Integer pdesc,Integer cid ,Integer ishot);
  PageInfo<ProductBean> getListProduct(Integer page,Integer pageSize);
  ProductBean queryProductBypid(Integer pid);
  Integer updateProduct(ProductBean product);
  Integer delProduct(Integer pid);
  Integer addProduct(ProductBean product);

  List<ProductBean> queryByCategoryId(Integer cid,String oder);

  List<ProductBean> selectByKey(String key,int cid,String order_by);
}
