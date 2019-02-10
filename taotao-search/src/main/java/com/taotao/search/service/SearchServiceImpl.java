package com.taotao.search.service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author chengpunan
 * @Description //TODO 商品搜索分页实现类
 * @Date 14:37 2019/2/10 0010
 * @Param 
 * @return 
 **/

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;
    @Override
    public SearchResult search(String queryString, int page, int rows) throws Exception {
//        创建查询对象
        SolrQuery query=new SolrQuery();
//        设置查询条件
        query.setQuery(queryString);
//        设置分页
        query.setStart((page-1)*rows);
        query.setRows(rows);
//      设置默认搜索域
        query.set("df","item_keywords");
//       设置高亮显示
        query.setHighlight(true);
//        设置要显示的域
        query.addHighlightField("item_title");
//        设置前缀
        query.setHighlightSimplePre("<em style=\"color:red\">");
//         设置后缀
        query.setHighlightSimplePost("</em>");
//        设置完成 执行
        SearchResult search = searchDao.search(query);
//        总记录数
        long recordCount = search.getRecordCount();
//        总记录数/当前页数
        long pageCount=recordCount/rows;
        if (recordCount % rows>0 ){
            pageCount ++;
        }
//        设置每页记录条数
        search.setPageCount(pageCount);
//        设置页数
        search.setCurPage(page);

        return search;
    }
}

