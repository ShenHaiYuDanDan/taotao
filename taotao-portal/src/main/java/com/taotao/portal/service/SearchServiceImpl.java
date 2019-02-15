package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService{
    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;
    @Override
    public SearchResult search(String queryString, int page) {
//        调用Search服务
//        查询参数
        Map<String,String> param=new HashMap<>();
        param.put("q",queryString);
        param.put("page",page+"");
        try{
//            调用服务 添加url，添加查询参数 q= queryString
            String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
//            转换成java字符串
            TaotaoResult result = TaotaoResult.formatToPojo(json, SearchResult.class);
//            返回状态正常
            if (result.getStatus()==200){
                SearchResult searchResult= (SearchResult) result.getData();
                return searchResult;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
