package com.veterine.excarrental;

public class Db_CarsListRecyclerViewData {
        //private int id;
        private String pass_no;
        private String image_name;
        private String image_path;
        private String price;


        Db_CarsListRecyclerViewData(String image_name, String pass_no, String price, String image_path) {
            //this.id = id;
            this.pass_no = pass_no;
            this.price = price;
            this.image_path = image_path;
            this.image_name = image_name;
        }

    public String getPass_no() {
        return pass_no;
    }

    public String getImage_name() {
        return image_name;
    }

    public String getImage_path() {
        return image_path;
    }

    public String getPrice() {
        return price;
    }
}
