package com.taotao.controller;

import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * 功能描述: 
 *
 * @param: 查询商品列表
 * @return: 
 * @auther: superman
 * @date: 2018/12/23 18:30
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;
    @SuppressWarnings({"rawtpyes","uncheckd"})
    @RequestMapping("/list")
    @ResponseBody
    public List categoryList(@RequestParam(value = "id",defaultValue = "0") Long perentId){

        List catList=new ArrayList();
        //查询数据库
        List<TbItemCat> list= itemCatService.getItemCatList(perentId);
        for (TbItemCat tbItemCat:list){
            Map node=new HashMap();
            node.put("id",tbItemCat.getId());
            node.put("text",tbItemCat.getName());
            //如果是父节点的话就设置成关闭状态， （？如果）如果是叶子节点就是open状态 如果是父节点就是closed 如果是子节点就是open
            node.put("state" ,tbItemCat.getIsParent()?"closed":"open");
            catList.add(node);

        }
        return catList;
    }
}
