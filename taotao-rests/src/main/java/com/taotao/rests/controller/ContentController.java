package com.taotao.rests.controller;

import com.taotao.pojo.TbContent;
import com.taotao.rests.service.ContentService;
import com.taotao.utils.ExceptionUtil;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentController {
    /**
     * @Author chengpunan  门户大广告位
     * @Description //TODO
     * @Date 19:41 2019/1/25 0025
     * @Param       需要调用ExceptionUtil工具类 打印取字符串的值。
     * @return
     **/

    @Autowired
    private ContentService contentService;
    @RequestMapping("/list/{contentCategoryId}")
    public TaotaoResult getContentList(@PathVariable long contentCategoryId){
        try {
            List<TbContent> list = contentService.getContentList(contentCategoryId);
            return TaotaoResult.ok(list);
        }catch (Exception e){
            e.getStackTrace();
//            错误500 并且返回一个字符串 使用ExceptionUtil工具类 返回e
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

    }
}
