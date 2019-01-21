package com.taotao.controller;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * 功能描述: 商品规格参数模板管理Controller
 *
 * @param:
 * @return:
 * @auther: superman
 * @date: 2018/12/31 16:03
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody                           //@@PathVariable 取{}号中的值 绑定给返回的值 取指定的值类似ID一类使用
    public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId){
        TaotaoResult result = itemParamService.getItemParamByCid(itemCatId);
        return result;
    }
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable Long cid,String paramData){
        /**
         *
         * 功能描述: 
         *
         * @param: [cid, paramData]
         * @return: com.taotao.utils.TaotaoResult
         * @auther: Superman
         * @date: 2019/1/3 20:46
         */
        
//        创建pojo对象
        TbItemParam itemparam=new TbItemParam();
        itemparam.setItemCatId(cid);
        itemparam.setParamData(paramData);
//        写入商品管理模板
        TaotaoResult result = itemParamService.insertItemParam(itemparam);
        return result;

    }
}
