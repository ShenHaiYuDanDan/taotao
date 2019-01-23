package com.taotao.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public EUDataGridResult getContentList(long category, int rows, int page) {
//        分页插件处理
        PageHelper.startPage(rows,page);
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(category);
//        list遍历接受所有结果集
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        PageInfo<TbContent> pageInfo=new PageInfo<TbContent>(list);
//        创建EasyUIDataGridResult 接受结果集
        EUDataGridResult result=new EUDataGridResult();
        result.setRows(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
/**
 *@ Author JackQin [www.dandan.top]
 *@ Date 2019/1/23 0023 16:19
 *@ Description  提交表单
 */

    @Override
    public TaotaoResult insetContent(TbContent tbContent) {
//        插入 补全Content
        tbContent.setUpdated(new Date());
        tbContent.setCreated(new Date());
        contentMapper.insert(tbContent);

        return TaotaoResult.ok();
    }
}
