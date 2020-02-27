package com.shopmanage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopmanage.entity.BlPageInfo;
import com.shopmanage.entity.ProductBean;
import com.shopmanage.entity.ResponseBean;
import com.shopmanage.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author cp
 */
@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/getProductList")
    @ResponseBody
     public BlPageInfo getProductList(@RequestParam("page") Integer page, @RequestParam("pageSize")Integer pageSize){
        PageInfo<ProductBean> data=  productService.getListProduct(page,pageSize) ;
        BlPageInfo blPageInfo =new BlPageInfo();
        blPageInfo.setTotal(data.getTotal());
        blPageInfo.setList(data.getList());
        log.info("blPageInfo"+blPageInfo);
        return blPageInfo;
     }

     @RequestMapping("/edit")
      public  String editProduct(Integer pid, Model model){
      ProductBean data=productService.queryProductBypid(pid);
      log.info("data"+data);
        model.addAttribute("product",data);
        return "page/edit_product.html";
     }

     @RequestMapping("/updateProduct")
     @ResponseBody
     public ResponseBean updateProduct(ProductBean product){

        Integer data =productService.updateProduct(product);
        ResponseBean<ProductBean> result= new ResponseBean<>();
        if(data==null){
            result.setCode(400);
        }
        return result;
     }
    @RequestMapping("/addProduct")
    @ResponseBody
    public ResponseBean addProduct(@RequestBody ProductBean product){
        Integer data =productService.addProduct(product);
        ResponseBean<ProductBean> result= new ResponseBean<>();
        if(data==null){
            result.setCode(400);
        }
        return result;
    }

    @RequestMapping("/queryProductByterm")
    @ResponseBody
    public PageInfo queryProductByterm( @RequestBody ProductBean productBean,
                                       @RequestParam(value="pn",defaultValue="1")Integer pn,
                                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        PageHelper.startPage(pn,pageSize);
        List<ProductBean> data=productService.queryProductByterm(productBean.getPname(),productBean.getPdesc(),productBean.getCid(),productBean.getIshot());
//        BlPageInfo<ProductBean>  reullt=new BlPageInfo<>();
        PageInfo pageInfo =new PageInfo(data);
//        reullt.setList(pageInfo.getList());
//        reullt.setTotal(pageInfo.getTotal());
        System.out.println("pageInfo"+pageInfo);
        return  pageInfo;
    }

    @RequestMapping("/delProdct")
    public  String delProdct(Integer pid){
        Integer data=productService.delProduct(pid);
        ResponseBean<ProductBean> result=new ResponseBean<>();
        if(data==null){
            result.setCode(400);
        }
        return "page/productlist.html";
    }
}
