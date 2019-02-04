package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbContent;
import com.taotao.utils.TaotaoResult;

import java.util.List;

public interface ContentService {
    EUDataGridResult getContentList(long category, int rows, int page);
    TaotaoResult insetContent(TbContent tbContent);
    TaotaoResult deleteContent(long id);

}
