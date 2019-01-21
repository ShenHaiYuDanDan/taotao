package com.taotao.service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService{
    @Autowired
    TbItemCatMapper tbItemCatMapper;
    @Override
    public List<TbItemCat> getItemCatList(Long parentId) {

        TbItemCatExample example=new TbItemCatExample();
//        设置查询条件
        TbItemCatExample.Criteria criteria=example.createCriteria();
//        根据parendId查询子节点
        criteria.andParentIdEqualTo(parentId);

//        返回子节点
        List<TbItemCat> list=tbItemCatMapper.selectByExample(example);
        return list;
    }
}
