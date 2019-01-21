package com.taotao.rests.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
public class CatNode {
//    @JsonProperty 在转换完属性之后 他的Key值是什么 默认是属性名 加上了这个注释 就用括号的属性来代替
    @JsonProperty("n")
    private  String name;
    @JsonProperty("u")
    private  String url;

    //    ?代表任何类型
    @JsonProperty("i")
    private List<?> item;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }


}
