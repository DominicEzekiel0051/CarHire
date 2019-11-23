package com.veterine.excarrental;

public class Db_SpListData {
    private int id;
    private String pass_no;
    private String image_name;
    private String image_path;
    private String price;


    Db_SpListData(int id, String image_name, String pass_no, String price, String image_path) {
        this.id = id;
        this.pass_no = pass_no;
        this.price = price;
        this.image_path = image_path;
        this.image_name = image_name;
    }

    public int getId() {
        return id;
    }

    String getPass_no() {
        return pass_no;
    }

    String getPrice() {
        return price;
    }

    String getImage_name() {
        return image_name;
    }

    String getImage_path() {
        return image_path;
    }



}
