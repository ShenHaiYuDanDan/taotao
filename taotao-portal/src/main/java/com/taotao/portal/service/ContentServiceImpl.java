package com.taotao.portal.service;

import com.taotao.pojo.TbContent;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;
import com.taotao.utils.TaotaoResult;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chengpunan
 * @Description //TODO 
 * @Date 15:08 2019/1/28 0028
 * @Param 调用服务层查询内容列表
 * @return 
 **/

@Service
public class ContentServiceImpl  implements  ContentService{

    @Value("${REST_BASE_URL}")
    private String    REST_BASE_URL;
    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;
    @Override
    public String getContentList() {
//        调用服务层的服务

        String doGet = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
//        把字符串转换成TaotaoResult
        try{
            TaotaoResult taotaoresult = TaotaoResult.formatToList(doGet, TbContent.class);
//            取出内容列表
            List<TbContent> list= (List<TbContent>) taotaoresult.getData();
            List<Map> resultList=new ArrayList<>();
//            创建一个新的jsp页面要求的pojo列表；
            for (TbContent tbContent:list){
                Map map=new HashMap();
                map.put("src",tbContent.getPic());
                map.put("srcB",tbContent.getPic2());
                map.put("height",240);
                map.put("widthB",670);
                map.put("alt",tbContent.getSubTitle());
                map.put("widthB",550);
                map.put("href",tbContent.getUrl());
                map.put("heightB",240);
                resultList.add(map);
            }
            return JsonUtils.objectToJson(resultList);
        }catch (Exception e){
                e.printStackTrace();
        }
        return null;
    }
}
