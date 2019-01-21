package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
/*
 *
 *
 * @author chenmc
 * @date 2018/11/30 20:25
 * @param 进入后台管理类
 * @return
 */
    @RequestMapping("/")
     public String showIndex(){
         return "index";
     }
     /*
      *  
      *   展示其他页面 分页
      * @author chenmc
      * @date 2018/11/30 20:18
      * @param
      * @return 
      */
     @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
     }
}
