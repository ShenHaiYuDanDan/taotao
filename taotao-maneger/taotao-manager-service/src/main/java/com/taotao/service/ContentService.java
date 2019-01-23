package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;

import java.util.List;

public interface ContentService {
    EUDataGridResult getContentList(long category, int rows, int page);
}
