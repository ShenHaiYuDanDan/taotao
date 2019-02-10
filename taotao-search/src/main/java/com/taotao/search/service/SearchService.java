package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * @Author chengpunan
 * @Description //TODO 商品搜索Service
 * @Date 14:35 2019/2/10 0010
 * @Param 
 * @return 
 **/

public interface SearchService {
    SearchResult search(String queryString,int page, int rows) throws Exception;
}
