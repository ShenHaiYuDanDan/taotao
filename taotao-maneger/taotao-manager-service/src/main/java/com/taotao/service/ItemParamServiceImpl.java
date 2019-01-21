package com.taotao.service;

import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 *
 * 功能描述:商品模板管理Service
 *
 * @param: 获取到了tb_item_param中的数据
 * @return:
 * @auther: superman
 * @date: 2018/12/31 16:04
 */
@Service
public class ItemParamServiceImpl implements ItemParamService{
    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public TaotaoResult getItemParamByCid(long cid) {
        TbItemParamExample example=new TbItemParamExample();
        Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);

        List<TbItemParam> list= tbItemParamMapper.selectByExampleWithBLOBs(example);
//        判断是否查询到结果
        if (list!=null&& list.size()>0){
//          查询到结果返回该结果
            return  TaotaoResult.ok(list.get(0));
        }
//        没查到结果返回null 并且提示是否添加该数据
        return TaotaoResult.ok();
    }
/**
 *
 * 功能描述: 商品管理模板提交表单更新
 * @param: 用来更新数据库 插入操作 insert(itenParam)
 * @return: 执行查询操作已在jsp里面方法体中写好
 * @auther: Superman
 * @date: 2019/1/3 20:22
 */
    public TaotaoResult insertItemParam(TbItemParam itemParam) {
//        补全这个pojo
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
//        插入到规格参数模板表
        tbItemParamMapper.insert(itemParam);
        return TaotaoResult.ok();
    }
}
