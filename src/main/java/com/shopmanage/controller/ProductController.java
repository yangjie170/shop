package com.shopmanage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopmanage.entity.BlPageInfo;
import com.shopmanage.entity.CategoryBean;
import com.shopmanage.entity.ProductBean;
import com.shopmanage.entity.ResponseBean;
import com.shopmanage.service.CategoryService;
import com.shopmanage.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author cp
 */
@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @RequestMapping("/toProductList")
    public String toProductList(Model model){
        List<CategoryBean> allCategory = categoryService.getAllCategory();
        model.addAttribute("categorys",allCategory);
        return "page/productlist.html";
    }

    @RequestMapping("/getProductList")
    @ResponseBody
     public BlPageInfo getProductList(@RequestParam("page") Integer page, @RequestParam("pageSize")Integer pageSize){
        PageInfo<ProductBean> data=  productService.getListProduct(page,pageSize) ;
        BlPageInfo blPageInfo =new BlPageInfo();
        blPageInfo.setTotal(data.getTotal());
        blPageInfo.setList(data.getList());
        return blPageInfo;
     }

     @RequestMapping("/edit")
      public  String editProduct(Integer pid, Model model){
      ProductBean data=productService.queryProductBypid(pid);
         List<CategoryBean> allCategory = categoryService.getAllCategory();
         System.out.println(allCategory);
         model.addAttribute("categorys",allCategory);
         System.out.println(data);
         model.addAttribute("product",data);
        return "page/edit_product.html";
     }

     @RequestMapping("/updateProduct")
     @ResponseBody
     public ResponseBean updateProduct(ProductBean product){

         String[] strs = product.getPimage().split("\\\\");
         String ss = strs[strs.length-2]+"\\"+strs[strs.length-1];
         product.setPimage(ss);

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
    public PageInfo queryProductByterm(ProductBean productBean,
                                       @RequestParam(value="pn",defaultValue="1")Integer pn,
                                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        PageHelper.startPage(pn,pageSize);
        List<ProductBean> data=productService.queryProductByterm(productBean.getPname(),productBean.getPdesc(),productBean.getCid(),productBean.getIshot());
        BlPageInfo<ProductBean>  reullt=new BlPageInfo<>();
        PageInfo pageInfo =new PageInfo(data);
        reullt.setList(pageInfo.getList());
        reullt.setTotal(pageInfo.getTotal());

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
