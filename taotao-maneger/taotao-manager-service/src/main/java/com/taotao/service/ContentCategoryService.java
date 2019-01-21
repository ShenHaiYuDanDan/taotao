package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.utils.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    List<EasyUITreeNode> getCategoryList(long parentId);
    TaotaoResult insertContentCategory(long parentId,String  name);
    TaotaoResult deleteContentCategory(long id);
    TaotaoResult updateContentCategory(long id,String name);
}
