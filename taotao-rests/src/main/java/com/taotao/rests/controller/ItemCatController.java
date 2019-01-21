package com.taotao.rests.controller;

import com.taotao.rests.pojo.CatResult;
import com.taotao.rests.service.ItemCatService;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

/**
 *
 * 功能描述:商品分类列表
 *  Jsonp的远程调用
 * @param:
 * @return:
 * @auther: Superman
 * @date: 2019/1/12 16:26
 */

@Controller
public class ItemCatController {
    @Autowired
    private  ItemCatService itemCatService;
//    @RequestMapping("/itemcat/list")
//    指定返回结果是一个json数据 字符集是utf-8
//    解决乱码第一种方法
//    @RequestMapping(value = "/itemcat/list",produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
//    @ResponseBody
//    public String getItemCatList(String callback){
//        CatResult catResult = itemCatService.getItemList();
////      把这个pojo转换成字符串 调用的jsonp 不能直接返回
//        String json= JsonUtils.objectToJson(catResult);
////      拼装返回值 返回一个字符串
//        String result=callback + (""+json+");");
//        return result;
//    }
//    解决乱码第二种方法
    @RequestMapping("/itemcat/list")
    @ResponseBody
    public Object getItemCatList(String callback){
        CatResult catResult = itemCatService.getItemList();
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}

