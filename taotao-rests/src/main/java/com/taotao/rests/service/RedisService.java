package com.taotao.rests.service;

import com.taotao.utils.TaotaoResult;

public interface RedisService {
    TaotaoResult syncContent(long contentCid);
}
