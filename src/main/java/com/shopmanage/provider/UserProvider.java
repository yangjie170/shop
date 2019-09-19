package com.shopmanage.provider;

import com.shopmanage.entity.ProductBean;
import org.apache.ibatis.jdbc.SQL;

/**
 * 动态sql( 例如 @DeleteProvider( type = UserProvider.class,method = "userProvider"))
 */
public class UserProvider {
    public String userProvider(ProductBean productBean){
        return  new SQL(){{
            SELECT("*");
            FROM("product");
            if(productBean.getPname()!=null){
                WHERE("pname like"+productBean.getPname());
            }
            if(productBean.getPdesc()!=null){
                WHERE("pdesc="+productBean.getPdesc());
            }
            if(productBean.getIshot()!=null){
                WHERE("is_hot="+productBean.getIshot());
            }
            if(productBean.getCid()!=null){
                WHERE("cid="+productBean.getCid());
            }
        }}.toString();

    }
}
