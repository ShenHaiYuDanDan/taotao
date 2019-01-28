package com.taotao.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientTest {

    @Test
    public void doGet()throws Exception{
//        先创建一个httpClient对象 它是一个抽象类 直接调用方法
        CloseableHttpClient httpClient= HttpClients.createDefault();
//        创建一个get对象
        HttpGet get=new HttpGet("https://www.sogou.com/");
//        执行请求
        CloseableHttpResponse response=httpClient.execute(get);
//        取响应的结果 取值 实体
        int code = response.getStatusLine().getStatusCode();
        System.out.println(code);
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity);
        System.out.println(string);
//        关闭httpClient
        response.close();
        httpClient.close();

    }
    @Test
    public void doGetWithParam()throws Exception{
//        创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        创建一个Url对象
        URIBuilder builder=new URIBuilder("https://www.sogou.com/web");
        builder.addParameter("query","盗墓笔记");
        HttpGet get=new HttpGet(builder.build());
        CloseableHttpResponse response= httpClient.execute(get);
        int code = response.getStatusLine().getStatusCode();
        System.out.println(code);
        HttpEntity entity = response.getEntity();
        String toString = EntityUtils.toString(entity,"utf-8");
        System.out.println(toString);
        response.close();
        httpClient.close();

    }
    @Test
    public void doPost()throws Exception{
//        创建HttpClient对象
        CloseableHttpClient closeableHttpClient=HttpClients.createDefault();
//        创建一个post对象
        HttpPost post=new HttpPost("http://localhost:8082/httpclient/post.html");
        CloseableHttpResponse response=closeableHttpClient.execute(post);
        String toString = EntityUtils.toString(response.getEntity());
        response.close();
        closeableHttpClient.close();
    }
    @Test
    public void doPostWithParam() throws IOException {
//        创建httpclient对象
        CloseableHttpClient client=HttpClients.createDefault();
//        创建post对象
        HttpPost post=new HttpPost("http://localhost:8082/httpclient/post.html");
//        创建一个Entity  模拟一个表单
        List<NameValuePair> kvlist=new ArrayList<>();
        kvlist.add(new BasicNameValuePair("username:","张三"));
        kvlist.add(new BasicNameValuePair("password:","123"));
//        包装成一个额Entity对象
        StringEntity entity= new UrlEncodedFormEntity(kvlist);
 //        设置请求内容
        post.setEntity(entity);
//        执行post请求
        CloseableHttpResponse response=client.execute(post);
        String s = EntityUtils.toString(response.getEntity());
        System.out.println(s);
        response.close();
        client.close();
    }
}
