package com.taotao.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;
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
        //缓存添加逻辑同步
        try{
            HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+tbContent.getCategoryId());
        }
        catch (Exception e
                ){
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }
/**
 * @Author chengpunan
 * @Description //TODO 删除大广告位广告信息
 * @Date 12:45 2019/2/4 0004
 * @Param 
 * @return 
 **/

    @Override
    public TaotaoResult deleteContent(long id) {

       contentMapper.deleteByPrimaryKey(id);
        return TaotaoResult.ok();
    }
    private List getDeleteContent(long id){
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(id);
        List<TbContent> list = contentMapper.selectByExample(example);
        return list;
    }

}
