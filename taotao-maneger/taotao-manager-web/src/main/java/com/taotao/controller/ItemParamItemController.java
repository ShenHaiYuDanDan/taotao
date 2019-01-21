package com.taotao.controller;

import com.taotao.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/showitem/{itemId}")
    /**
     *
     * 功能描述: 返回给前端一个视图 itemId 用来查询 Model 用来指定jsp
     *
     * @param: [itemId, model]
     * @return: java.lang.String
     * @auther: Superman
     * @date: 2019/1/7 19:40
     */

    public String showItemParam(@PathVariable Long itemId, Model model) {
        String string = itemParamItemService.getItemParamByItemId(itemId);
        model.addAttribute("itemParam", string);
//        此处的item对应JSP目录下的item.jsp
        return "item";
    }
}
