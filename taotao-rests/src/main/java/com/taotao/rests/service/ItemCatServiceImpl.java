package com.taotao.rests.service;

import com.alibaba.druid.sql.visitor.functions.If;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rests.dao.JedisClient;
import com.taotao.rests.pojo.CatNode;
import com.taotao.rests.pojo.CatResult;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 功能描述: 分类服务
 *
 * @param:
 * @return:
 * @auther: Superman
 * @date: 2019/1/12 15:46
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper  itemCatMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY1}")

    private String INDEX_CONTENT_REDIS_KEY1;
    public CatResult getItemList() {
        CatResult catResult=new CatResult();
//        查询分类列表 此处调用下面方法
        catResult.setData(getCatList(0));
        return catResult;
    }
    /**
     *
     * 功能描述:查询分类列表的方法
     *
     * @param:
     * @return:
     * @auther: Superman
     * @date: 2019/1/12 15:52
     */

    private List<?> getCatList(long parenId) {
//        取缓存
        try{

            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY1, parenId + "");
            if (!StringUtils.isBlank(result)){
                List<TbItemCat> jsonToList = JsonUtils.jsonToList(result, TbItemCat.class);
                return  jsonToList;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
//        创建查询条件
        TbItemCatExample example = new TbItemCatExample();
//        查询方法
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parenId);
//        执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        //       返回值List
        List catNodeList = new ArrayList();
//      向list中添加节点
        int count=0;
        for (TbItemCat tbItemCat : list) {

//            第一层 判断是是否为父节点
            if(tbItemCat.getIsParent()) {

                CatNode catNode = new CatNode();
                if (parenId == 0) {
//               parenId==0 则拼接a标签
                    catNode.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
                } else {
//             否则  parenId==0 就不用在拼接a标签了
                    catNode.setName(tbItemCat.getName());
                }
                catNode.setUrl("/products/" + tbItemCat.getId() + ".html");
//            递归 调用自己然后取id 这样就查询到子节点
                catNode.setItem(getCatList(tbItemCat.getId()));
                catNodeList.add(catNode);
                count ++;
//               第一层之取14条记录
                if ( parenId == 0&&count >= 14){
                    break;
                }
//                如果是叶子节点 直接添加一个字符串 不是叶子节点创建一个CatNode 第一次需要+a标签
            }else {
                catNodeList.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
            }
        }
//        向redis添加缓存
        try{
            String toJson = JsonUtils.objectToJson(catNodeList);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY1,parenId+"",toJson);

        }catch (Exception e){
            e.printStackTrace();
        }
        return catNodeList;
    }
}
