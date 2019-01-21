package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * 功能描述:内容分类管理

 * @param:
 * @return:
 * @auther: Superman
 * @date: 2019/1/14 14:50
 */

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getCategoryList(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        List<EasyUITreeNode> resultList = new ArrayList<>();
//        比较取值
        for (TbContentCategory tbContentCategory : list) {
//            创建节点
            EasyUITreeNode node = new EasyUITreeNode();
//            node.setId = 等于(tbContentCategory.getId());
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
//            判断是否位父节点 是父节点就是 closed 不是就是open
            if (tbContentCategory.getIsParent()) {
                node.setState("closed");
            } else {
                node.setState("open");
            }
            resultList.add(node);
        }

        return resultList;
    }

    @Override
    public TaotaoResult insertContentCategory(long parentId, String name) {
//        创建一个pojo
        TbContentCategory category = new TbContentCategory();

        category.setParentId(parentId);
        category.setName(name);
        category.setIsParent(false);
        category.setStatus(1);
        category.setUpdated(new Date());
        category.setSortOrder(1);
        category.setUpdated(new Date());
        tbContentCategoryMapper.insert(category);
        //        添加纪录
//        查看父节点的isParent是否位true ,如果不是true 改成true
        TbContentCategory parentCat = tbContentCategoryMapper.selectByPrimaryKey(parentId);
//        查看是否为父节点
        if (!parentCat.getIsParent()) {
//            不是父节点 改为true
            parentCat.setIsParent(true);
//            更新父节点
            tbContentCategoryMapper.updateByPrimaryKey(parentCat);
        }
        return TaotaoResult.ok(category);
    }
/**
 *
 * 功能描述:删除商品分类 执行递归操作
 *      JSP页面写了删除方法 我们只需要调用，来遍历传值给Controller告诉jsp页面下的方法删除那个分类即可
 * @param:
 * @return:
 * @auther: Superman
 * @date: 2019/1/17 13:55
 */

    public TaotaoResult deleteContentCategory(long id) {
        TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
//        判断是否为子节点
        if (contentCategory.getIsParent()) {
//            递归删除
            List<TbContentCategory> contentCateListByParentId = getContentCateListByParentId(id);
            for (TbContentCategory tbContentCategory : contentCateListByParentId) {
                deleteContentCategory(contentCategory.getParentId());
                //            判断父类还没有有子类节点
                if (getContentCateListByParentId(contentCategory.getParentId()).size() == 1) {
//                        没有就把父节点标记为子节点
                    TbContentCategory contentCategory1 = tbContentCategoryMapper.selectByPrimaryKey(contentCategory.getId());
                    contentCategory.setIsParent(false);
//                    更新此节点
                    tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
                }
            }


        }
//        删除子节点
        tbContentCategoryMapper.deleteByPrimaryKey(id);
        return TaotaoResult.ok();
    }
/**
 *
 * 功能描述:修改名称
 *
 * @param:
 * @return:
 * @auther: Superman
 * @date: 2019/1/17 18:59
 */

    @Override
    public TaotaoResult updateContentCategory(long id, String name) {
//        根据id更改 用户当前点击的页面
        TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
//        要更改的名称
        contentCategory.setName(name);
//        添加更新节点
        tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
        return TaotaoResult.ok();
    }

    /**
 *
 * 功能描述:创建一个查询 此方法是私有 用这个方法来遍历取值 方便在上面直接比较
 *
 * @param:
 * @return:
 * @auther: Superman
 * @date: 2019/1/17 13:54
 */

    private List<TbContentCategory> getContentCateListByParentId(Long id){
        TbContentCategoryExample example=new TbContentCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        return list;
    }

}
