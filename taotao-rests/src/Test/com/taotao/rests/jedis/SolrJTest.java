package com.taotao.rests.jedis;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {
/**
 * @Author chengpunan
 * @Description //TODO 测试写入
 * @Date 11:08 2019/2/10 0010
 * @Param 
 * @return 
 **/

    @Test
    public void addDocument()throws Exception{
//        创建一个连接
        SolrServer solrServer=new HttpSolrServer("http://localhost:8080/solr/core");
//        创建一个文档对象
        SolrInputDocument document= new SolrInputDocument();
        document.addField("id","test002");
        document.addField("item_title","测试2");
        document.addField("item_price",123);
//        把文档对象写入索引库
        solrServer.add(document);
//        提交
        solrServer.commit();
    }
/**
 * @Author chengpunan
 * @Description //TODO  测试删除
 * @Date 11:08 2019/2/10 0010
 * @Param
 * @return
 **/

    @Test
    public void deleteDocument()throws  Exception{
        SolrServer solrServer=new HttpSolrServer("http://localhost:8080/solr/core");
//        根据id删除
//        solrServer.deleteById("test001");
//        跟据查询条件删除
        solrServer.deleteByQuery("*:*");
        solrServer.commit();

        }
}
