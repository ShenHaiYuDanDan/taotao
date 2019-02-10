package com.taotao.search.service;

import com.taotao.utils.TaotaoResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface ItemService {
    TaotaoResult importAllItems() throws IOException, SolrServerException, Exception;
}
