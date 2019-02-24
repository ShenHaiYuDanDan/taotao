package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;
    /**
     *@ Author JackQin [www.dandan.top]
     *@ Date 2019/1/23 0023 16:22
     *@ Description广告位内容展示
     */


    @RequestMapping("/query/list")
    @ResponseBody
    public EUDataGridResult getContentList(Long categoryId,int page,int rows){
        EUDataGridResult list = contentService.getContentList(categoryId, page, rows);
        return list;
    }
    /**
     *@ Author JackQin [www.dandan.top]
     *@ Date 2019/1/23 0023 16:21
     *@ Description 广告位内容添加
     */

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult insetContent(TbContent tbContent){
        TaotaoResult result = contentService.insetContent(tbContent);
        return result;

    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteList(Long ids){
        /**
         *
         * 功能描述: 广告位功能删除
         *
         * @param: [ids]
         * @return: com.taotao.utils.TaotaoResult
         * @auther: Superman
         * @date: 2019/2/24 13:44
         */

        TaotaoResult result = contentService.deleteContent(ids);
        return result;
    }


}
