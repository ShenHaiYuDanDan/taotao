package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *@ Author JackQin [www.dandan.top]
 *@ Date 2019/1/23 0023 13:10
 *@ Description
 * 内容管理展示
 */

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public EUDataGridResult getContentList(Long categoryId,int page,int rows){
        EUDataGridResult list = contentService.getContentList(categoryId, page, rows);
        return list;
    }
}
