package com.taotao.rests.jedis;

import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.security.PublicKey;
import java.util.HashSet;

public class testJedis {
    @Test
    public void testJedinsSinle(){
//        创建一个jedis对象
        Jedis jedis=new Jedis("127.0.0.1",6379);
//        调用jedis对象方法，方法名称和redis的命令的一致
        jedis.set("key1","jedis test");
        String s = jedis.get("key1");
        System.out.println(s);
//          关闭Jedis
        jedis.close();

    }
    /*
* 使用连接池
*
* */
    @Test
    public void testJedisPool(){
//        创建一个Jedis连接池
        JedisPool jedisPool=new JedisPool("127.0.0.1",6379);
//       从连接池获得Jedis对象
        Jedis jedis = jedisPool.getResource();
        String s = jedis.get("key1");
        System.out.println(s);
        jedis.close();
    }
    /**     集群版测试
     * @Author chengpunan
     * @Description //TODO
     * @Date 22:30 2019/1/28 0028
     * @Param
     * @return
     **/
    @Test
    public void testJedisCluster(){
        HashSet<HostAndPort> nodes=new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1",6379));
        nodes.add(new HostAndPort("127.0.0.1",6380));
        nodes.add(new HostAndPort("127.0.0.1",6381));
        nodes.add(new HostAndPort("127.0.0.1",6382));
        nodes.add(new HostAndPort("127.0.0.1",6383 ));
        nodes.add(new HostAndPort("127.0.0.1",6384));
        JedisCluster cluster=new JedisCluster(nodes);
        cluster.set("key2","1000");
        cluster.set("key1","21111");
        String s = cluster.get("key1");
        System.out.println(s);
        cluster.close();
    }
//
    @Test
    public void testSpringJedisSingle(){
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContent-*.xml");
        JedisPool pool= (JedisPool) context.getBean("redisClient");
        Jedis resource = pool.getResource();
        String s = resource.get("key");
        System.out.println(s);
        pool.close();
        resource.close();
    }
    @Test
    public void testSpringJedisCluster(){
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisCluster cluster= (JedisCluster) context.getBean("redisClient");
        String s = cluster.get("key1");
        System.out.println(s);
        cluster.close();
    }

}
