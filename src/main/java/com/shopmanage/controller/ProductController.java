package com.shopmanage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopmanage.entity.*;
import com.shopmanage.entity.DTO.ProductDTO;
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
import java.util.*;


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
    @RequestMapping("/getProductByCategory")
    @ResponseBody
    public String getProductByCategory(Integer cid){
        List list =productService.queryByCategoryId(cid);
        String data= (String) JSON.toJSON(list);
        return data;
    }

    @RequestMapping("/getOneProduct.do")
    @ResponseBody
    public RspOne getOneProduct(@RequestParam Map<String,String> map){
        int pid = JSONObject.parseObject(map.get("json")).getObject("goods_id",int.class);
        ProductBean productBean = productService.queryProductBypid(pid);
        RspOne rsp = new RspOne();
        if (productBean==null){
            rsp.setStatus(new Status(1,200,"查询错误"));
            throw new RuntimeException("没查到id为"+pid+"的产品");
        }
        Picture picture = new Picture();
        picture.setUrl(productBean.getPimage());
        picture.setSmall(productBean.getPimage());
        picture.setThumb(productBean.getPimage());
        ProductDTO productDTO = new ProductDTO();
        productDTO.initProduct(productBean);
        List<Picture> list = new ArrayList<>();
        list.add(picture);
        productDTO.setPictures(list);
        productDTO.setPicture(picture);
        rsp.setData(productDTO);
        rsp.setStatus(new Status(1,200,"请求成功"));
        return rsp;
    }
    @RequestMapping("/search.do")
    @ResponseBody
    public Rsp searchProduct(@RequestParam Map<String,String> map){
        String s= null;
        for (Map.Entry<String, String> entry:map.entrySet()){
//            System.out.print("得到键为：==="+entry.getKey());
//            System.out.println("得到值为：==="+entry.getValue());
            s=entry.getValue();
        }
        JSONObject jsonObject = JSON.parseObject(s);
        JSONObject j = JSONObject.parseObject(jsonObject.getString("filter"));
        JSONObject j1 = JSONObject.parseObject(jsonObject.getString("pagination"));
        Filter filter = JSON.toJavaObject(j,Filter.class);
        Pagination pagination = JSON.toJavaObject(j1,Pagination.class);
        Rsp rsp = new Rsp();
        Paginated paginated = new Paginated();
        paginated.setCount(pagination.getCount());
        List<ProductBean> list = productService.queryByCategoryId(filter.getCategory_id());
        List<ProductDTO> list1 = new java.util.ArrayList<>(Collections.emptyList());
        for (ProductBean productBean :list) {
            Picture picture = new Picture();
            picture.setSmall(productBean.getPimage());
            picture.setUrl(productBean.getPimage());
            picture.setThumb(productBean.getPimage());
            ProductDTO productDTO = new ProductDTO();
            productDTO.setPicture(picture);
            productDTO.initProduct(productBean);
//            System.out.println(productDTO.getPicture().toString());
            list1.add(productDTO);
        }
        paginated.setTotal(list1.size());
        paginated.setMore(list1.size()/(pagination.getPage()*pagination.getCount())<1?1:0);
        rsp.setData(list1);
        rsp.setStatus(new Status(1,200,"请求成功"));
        rsp.setPaginated(paginated);
        return rsp;
    }

    public static class RspOne extends Response{
        ProductDTO data;

        public ProductDTO getData() {
            return data;
        }

        public void setData(ProductDTO data) {
            this.data = data;
        }
    }

    //处理集合的响应类
    public static  class Rsp extends Response{
        List<ProductDTO> data;
        Paginated paginated;
        Status status;

        @Override
        public Status getStatus() {
            return status;
        }

        @Override
        public void setStatus(Status status) {
            this.status = status;
        }

        public Paginated getPaginated() {
            return paginated;
        }

        public void setPaginated(Paginated paginated) {
            this.paginated = paginated;
        }

        public List<ProductDTO> getData() {
            return data;
        }

        public void setData(List<ProductDTO> data) {
            this.data = data;
        }
    }
    public static class Paginated{
        private int total;
        private int count;
        private int more;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getMore() {
            return more;
        }

        public void setMore(int more) {
            this.more = more;
        }
    }


}
