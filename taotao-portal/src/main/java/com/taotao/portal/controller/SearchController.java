package com.taotao.portal.controller;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    @RequestMapping("/search")
    public String search(@RequestParam("q")String queryString, @RequestParam("1")Integer page, Model model){
        if (queryString!=null){
            try{
                //            解决乱码
                queryString=new String(queryString.getBytes("iso8859-1"),"utf-8");
            }catch (Exception e) {
                //                解决乱码失败就报个错
                e.printStackTrace();
            }
        }
        SearchResult search = searchService.search(queryString, page);
//        添加查询关键字
        model.addAttribute("query",queryString);
//        查询结果的总页数
        model.addAttribute("totalPages",search.getPageCount());
//        返回一个查询列表
        model.addAttribute("itemList",search.getItemList());
//        设置分页
        model.addAttribute("page",page);
        return "search";
    }

}
