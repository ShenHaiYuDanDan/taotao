package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.utils.IDUtils;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/*
 *
 * 商品管理Service
 * @author chenmc
 * @date 2018/11/23 19:38
 * @param
 * @return
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDesc;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(Long itemId) {

        //TbItem item = itemMapper.selectByPrimaryKey(itemId);
        //添加查询条件
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }
/*
 *  
 *  商品列表查询
 * @author chenmc
 * @date 2018/11/30 21:45
 * @param 
 * @return 
 */
    @Override
    public EUDataGridResult getItemList(int page, int rows) {
//        查询商品列表
        TbItemExample example=new TbItemExample();
////        分页处理
        PageHelper.startPage(page,rows);
//        list取值     使用TbItemMapper来调用TbItemExample中的selectByExample方法 （TbItemMpper注入到其中）来查询所数据(example)
        List<TbItem> list=itemMapper.selectByExample(example);
//        创建一个返回对象
        EUDataGridResult result=new EUDataGridResult();
//        把list返回给前端 并且分页
        result.setRows(list);
//        取记录总条数
        PageInfo<TbItem>  pageInfo=new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
       return result;
    }
    /**
     *
     * 功能描述:
     *
     * @param:  添加商品写入数据库 工具类 pojo
     * @return:  提交frome表单
     * @auther: superman  数据库要写入自动递增不然会报null
     * @date: 2018/12/23 14:08
     */
    public TaotaoResult createItem(TbItem item,String desc,String itemParam)throws  Exception {
//        item 补全
//        生成商品ID
        Long itemID= IDUtils.genItemId();
        item.setCid(itemID);
//        商品的状态 1-正常 2-下架 3-删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
//        插入数据库
        itemMapper.insert(item);
//        在这里调用下列方法  事务回滚 不要加 try catch
//        添加商品描述信息
        TaotaoResult result=insertItmDesc(itemID,desc);
//        TaotaoResult 有getStatus方法 200=正常
        if (result.getStatus() !=200){
                throw new Exception();
        }
        result=insertItemParamItem(itemID,itemParam);
        if (result.getStatus()!=200){
            throw new Exception();
        }
        return TaotaoResult.ok();
    }/**
     *
     * 功能描述: 添加商品描述  在一个事务里面 需要在上方的方法体中调用
     *
     * @param: [item, desc]
     * @return: [item, desc]
     * @auther: superman
     * @date: 2018/12/24 17:43
     */
    private TaotaoResult insertItmDesc(Long itemId,String desc){
//    调用 TbItemDesc 和相应的mapper方法 来把信息写入到 数据库
        TbItemDesc tbItemDesc=new TbItemDesc();
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(itemId);

        tbItemDesc.setUpdated(new Date());
        tbItemDesc.setCreated(new Date());
        itemDesc.insert(tbItemDesc);
        return TaotaoResult.ok();
    }
    /**
     *
     * 功能描述: 添加商品规格 使用TbItemParamItem方法和mapper pojo实现
     *
     * @param:
     * @return:
     * @auther: superman
     * @date: 2018/12/25 17:28
     */
//    private TaotaoResult insertParam(Long itemID,String itemParam){
//        TbItemParamItem tbItemParamItem=new TbItemParamItem();
//        tbItemParamItem.setId(itemID);
//        tbItemParamItem.setParamData(itemParam);
//        tbItemParamItem.setUpdated(new Date());
//        tbItemParamItem.setCreated(new Date());
//        paramItemMapper.insert(tbItemParamItem);
//        return TaotaoResult.ok();
//
//    }
    private TaotaoResult insertItemParamItem(Long itemId,String itemParam){

        TbItemParamItem itemParamItem=new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
//        向表中插入数据
        itemParamItemMapper.insert(itemParamItem);
        return TaotaoResult.ok();
    }
}

