package com.example.nbshoping.goods;

import java.io.Serializable;


//单个货物信息数据格式
public class ProductiveBean implements Serializable {

    /**
     * code : 200
     * message : 查询成功
     * data : {"id":59,"count":550,"categoryId":9,"name":"少儿编程","info":"Scratch少儿编程一学就会","photo":"/image/commodity/shaoerbiancheng.png","price":120}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 59
         * count : 550
         * categoryId : 9
         * name : 少儿编程
         * info : Scratch少儿编程一学就会
         * photo : /image/commodity/shaoerbiancheng.png
         * price : 120.0
         */

        private int id;
        private int count;
        private int categoryId;
        private String name;
        private String info;
        private String photo;
        private double price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
