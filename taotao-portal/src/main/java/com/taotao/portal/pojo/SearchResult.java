package com.taotao.portal.pojo;

import java.util.List;

public class SearchResult {
//    商品列表
    private List<Item> itemList;
//    总记录数
    private long   recordCount;
//    总页数
    private long   pageCount;
//    当前页
    private long  curPage;

    public List<Item> getItemList() {
        return itemList;
    }

    public SearchResult setItemList(List<Item> itemList) {
        this.itemList = itemList;
        return this;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public SearchResult setRecordCount(long recordCount) {
        this.recordCount = recordCount;
        return this;
    }

    public long getPageCount() {
        return pageCount;
    }

    public SearchResult setPageCount(long pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public long getCurPage() {
        return curPage;
    }

    public SearchResult setCurPage(long curPage) {
        this.curPage = curPage;
        return this;
    }
}
