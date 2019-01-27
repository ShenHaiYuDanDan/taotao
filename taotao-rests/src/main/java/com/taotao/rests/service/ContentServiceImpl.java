package com.taotao.rests.service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<TbContent> getContentList(long contentCid) {

        
//        根据id查询列表
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
//        执行list
        List<TbContent> list = tbContentMapper.selectByExample(example);

        return list;


    }
}
