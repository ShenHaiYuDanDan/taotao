package com.taotao.search.pojo;

public class Item {

    private long id;
    private String title;
    private String sell_point;
    private long price;
    private String image;
    private String category_name;
    private String item_desc;
    public long getId() {
        return id;
    }

    public Item setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Item setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSell_point() {
        return sell_point;
    }

    public Item setSell_point(String sell_point) {
        this.sell_point = sell_point;
        return this;
    }

    public long getPrice() {
        return price;
    }

    public Item setPrice(long price) {
        this.price = price;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Item setImage(String image) {
        this.image = image;
        return this;
    }

    public String getCategory_name() {
        return category_name;
    }

    public Item setCategory_name(String category_name) {
        this.category_name = category_name;
        return this;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public Item setItem_desc(String item_desc) {
        this.item_desc = item_desc;
        return this;
    }


}
