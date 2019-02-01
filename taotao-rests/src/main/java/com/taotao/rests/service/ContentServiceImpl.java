package com.taotao.rests.service;

import org.apache.commons.lang3.StringUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rests.dao.JedisClient;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

  /*      内容管理
         * @Author chengpunan
         * @Description //TODO
         * @Date 10:30 2019/1/25 0025
         * @Param [contentCid]
         * @return java.util.List<com.taotao.pojo.TbContent>
         **/
import java.util.List;
@Service
public class ContentServiceImpl  implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;
    @Override
    public List<TbContent> getContentList(long contentCid) {
       //从缓存中取内容
        try {
            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid + "");
            if (!StringUtils.isBlank(result)) {
                //把字符串转换成list
                List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
                return resultList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        根据id查询列表
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
//        执行list
        List<TbContent> list = tbContentMapper.selectByExample(example);
//        从缓存中添加内容
        try{
//            把list转换成字符串
            String cacheStirng = JsonUtils.objectToJson(list);
//            使用哈希set
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY,contentCid+"",cacheStirng);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;


    }
}
