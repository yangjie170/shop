package com.shopmanage.mapper;

import com.shopmanage.entity.ProductBean;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductMapper {
    /**
     *
     * @param product
     * @return
     */
   @Insert("insert into product(pname,mark_price,shop_price,pimage,pdate,is_hot,pdesc,pflag,cid,detial) " +
           "values(#{pname}," +
           "#{markprice}," +
           "#{shopprice}," +
           "#{pimage}," +
           "#{pdate}," +
           "#{ishot}," +
           "#{pdesc}," +
           "#{pflag}," +
           "#{cid},"+
           "#{detial})")
   @Options(useGeneratedKeys =true,keyColumn = "pid",keyProperty = "pid")
   Integer addProduct(ProductBean product);
    /***
     * 获取product列表
     * @return
     */
     @Select("select product.pid ,product.pname,product.mark_price,product.shop_price,product.pimage,product.pdate,product.is_hot,product.pdesc,product.pflag ,product.detial,category.cname from product,category where product.cid=category.cid order by pdate desc")
    List<ProductBean> getListProduct();

    /**
     * 修改商品信息
     * @param product
     * @return
     */
     @Update("update product set pname=#{pname}" +
             ",mark_price=#{markprice}" +
             ",shop_price=#{shopprice}" +
             ",pimage=#{pimage}" +
             ",pdate=#{pdate}" +
             ",is_hot=#{ishot}" +
             ",pdesc=#{pdesc}" +
             ",pflag=#{pflag}" +
             ",cid=#{cid}," +
             "detial=#{detial} where pid=#{pid} ")
     Integer updataProduct(ProductBean product);

    /**
     * 删除商品
     * @param pid
     * @return
     */
     @Delete("delete  from product where pid=#{pid}")
     Integer delProduct(Integer pid);

    /**
     * 多条件查询
     * @param content
     * @return
     */
    @Select("select product.pid " +
            ",product.pname," +
            "product.mark_price," +
            "product.shop_price" +
            ",product.pimage," +
            "product.pdate" +
            ",product.is_hot" +
            ",product.pdesc," +
            "product.pflag ," +
            "product.detial," +
            "product.cid," +
            "category.cname " +
            "from product,category " +
            "where  ${content} order by pdate desc")
      List<ProductBean> queryProductByterm(@Param("content") String content);

      @Select("select product.pid " +
              ",product.pname," +
              "product.mark_price," +
              "product.shop_price" +
              ",product.pimage," +
              "product.pdate" +
              ",product.is_hot" +
              ",product.pdesc," +
              "product.pflag ," +
              "product.detial," +
              "product.cid," +
              "category.cname " +
              "from product,category " +
              "where product.cid=category.cid  and pid=#{pid} order by pdate desc")
      ProductBean queryProductBypid(Integer pid);









}
