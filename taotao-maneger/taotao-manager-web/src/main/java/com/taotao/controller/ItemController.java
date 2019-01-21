package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 *  商品管理Controller
 *
 * @author chenmc
 * @date 2018/11/23 19:47
 * @param
 * @return
 */

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }
    /*
     *      方法对应WEB-INF下方的JSP
     *    商品列表查询返回方法
     *    接受页面传递过来的参数 page rows 返回JSON格式 使用注解@ResponseBody
     * @author chenmc
     * @date 2018/11/30 21:58
     * @param
     * @return
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows){
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }
    /**
     *
     * 功能描述: 
     *
     * @param: 传入商品信息   使用pojo接受 调用Service 返回TaotaoResult对象
     * @return: 返回JSOn数据 需要使用@ResponseBody注解
     * @auther: superman 需要在数据库设置自动递增不然报错空指针
     * @date: 2018/12/23 14:31
     */
    @RequestMapping(value ="/item/save",method = RequestMethod.POST)
    @ResponseBody
//     注意！ 此处返还的方法要与jsp相同 jsp内容是   <input type="hidden" name="itemParams"/>
//     此处返回参数名不同 将不会执行转换JSON方法到数据库 此处参数名是itemParams 返回方法也必须相同 不然是long
    private TaotaoResult createItem(TbItem item,String desc,String itemParams)throws  Exception{
        TaotaoResult result = itemService.createItem(item,desc,itemParams);
        return  result;
    }



}
