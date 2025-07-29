package com.v2v.ecommerceapp;

public class Product {
    private String id;
    private String name;
    private String price;
    private String desc;
    private String imageUri;

    public Product() {
        // Required empty constructor for Firebase
    }

    public Product(String id, String name, String price, String desc, String imageUri) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.imageUri = imageUri;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageUri() {
        return imageUri;
    }
}
