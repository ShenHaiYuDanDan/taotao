package com.taotao.rests.service;

import com.taotao.rests.dao.JedisClient;
import com.taotao.utils.ExceptionUtil;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
/**
 * @Author chengpunan
 * @Description //TODO 删除Reids对应的key的id
 * @Date 12:59 2019/2/3 0003
 * @Param
 * @return
 **/

    @Autowired
    private JedisClient jedisClient;
    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;
    @Override
    public TaotaoResult syncContent(long contentCid) {
        try {
//            使用jedisClient调用删除方法hdel，命名就是删除的指令。只需要执行即可
            jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");
        }catch (Exception e){
//            删除错误，返回500错误。打印异常信息。
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }
}
