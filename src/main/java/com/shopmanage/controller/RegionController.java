package com.shopmanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.shopmanage.entity.Region;
import com.shopmanage.entity.Response;
import com.shopmanage.entity.Status;
import com.shopmanage.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;
    @RequestMapping("/getOne")
    @ResponseBody
    public Rsp region(@RequestParam Map<String,String> map){
        JSONObject jsonObject = JSONObject.parseObject(map.get("json"));
        int parent_id = Integer.parseInt(jsonObject.getString("parent_id"));
        List<Region> list = regionService.selectAllReginByParentId(parent_id);
        Rsp rsp = new Rsp();
        Rsp.Data data = new Rsp.Data();
        data.setRegions(list);
        data.setMore(1);
        rsp.setData(data);
        rsp.setStatus(new Status(1,200,"请求成功"));
        return rsp;
    }
    public static class Rsp extends Response {
        Data data;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public static class Data{
            private List<Region>  regions;
            private int more;

            public List<Region> getRegions() {
                return regions;
            }

            public void setRegions(List<Region> regions) {
                this.regions = regions;
            }

            public int getMore() {
                return more;
            }

            public void setMore(int more) {
                this.more = more;
            }
        }
    }
}
