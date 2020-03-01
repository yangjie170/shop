package com.shopmanage.service.impl;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopmanage.entity.ProductBean;
import com.shopmanage.mapper.ProductMapper;
import com.shopmanage.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author cp
 */
   @Service
   @Slf4j
public class ProductServiceImpl  implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public List<ProductBean> queryProductByterm( String pname,Integer pdesc,Integer cid,Integer ishot) {
        StringBuilder sb =new StringBuilder(" 1= 1 AND  product.cid=category.cid");
        if(pname.length()>0) {
            sb.append(" AND ").append("pname like").append("'%").append(pname).append("%'");
        }
        if(pdesc!=null) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("pdesc= ").append(pdesc);
        }
        if(cid!=null) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("product.cid= ").append(cid);
        }
        if(ishot!=null) {
            if (sb.length() > 0) {
                sb.append(" AND ");
            }
            sb.append("is_hot= ").append(ishot);
        }
        return productMapper.queryProductByterm(sb.toString());
    }

    @Override
    public PageInfo<ProductBean> getListProduct(@RequestParam(value="page",defaultValue="1")Integer page, @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<ProductBean> productList= productMapper.getListProduct();
        PageInfo<ProductBean> productInfo= new PageInfo<>(productList);

        return productInfo;
    }

    @Override
    public ProductBean queryProductBypid(Integer pid) {
        return productMapper.queryProductBypid(pid);
    }

    @Override
    public Integer updateProduct(ProductBean product) {

        return productMapper.updataProduct(product);
    }

    @Override
    public Integer delProduct(Integer pid) {
        return productMapper.delProduct(pid);
    }

    @Override
    public Integer addProduct(ProductBean product) {
        return productMapper.addProduct(product);

    }


}
