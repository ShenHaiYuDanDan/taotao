package com.taotao.rests.controller;

import com.taotao.rests.service.RedisService;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cache/sync")
public class RedisController {
    /**
     * @Author chengpunan
     * @Description //TODO 缓存同步Controller
     * @Date 13:02 2019/2/3 0003
     * @Param 
     * @return 
     **/
    @Autowired
    private RedisService redisService;
    @RequestMapping("/content/{contentCid}")
    @ResponseBody
    public TaotaoResult contentCachesync(@PathVariable long contentCid){
        TaotaoResult result = redisService.syncContent(contentCid);
        return result;
    }

}
