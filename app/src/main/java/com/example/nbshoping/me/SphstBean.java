package com.example.nbshoping.me;

import java.io.Serializable;
import java.util.List;

public class SphstBean implements Serializable {


    /**
     * code : 200
     * message : 查询成功
     * data : [{"id":80,"commodityId":35,"count":1,"userId":95,"commodityName":"公主裙","commodityInfo":"桃丝花园-浪漫印花花朵连衣裙花色吊带高腰A字公主裙","commodityPhoto":"/image/commodity/gongzhuqun.png","univalence":199,"totalprices":199},{"id":81,"commodityId":35,"count":1,"userId":95,"commodityName":"公主裙","commodityInfo":"桃丝花园-浪漫印花花朵连衣裙花色吊带高腰A字公主裙","commodityPhoto":"/image/commodity/gongzhuqun.png","univalence":199,"totalprices":199},{"id":82,"commodityId":35,"count":1,"userId":95,"commodityName":"公主裙","commodityInfo":"桃丝花园-浪漫印花花朵连衣裙花色吊带高腰A字公主裙","commodityPhoto":"/image/commodity/gongzhuqun.png","univalence":199,"totalprices":199},{"id":83,"commodityId":35,"count":1,"userId":95,"commodityName":"公主裙","commodityInfo":"桃丝花园-浪漫印花花朵连衣裙花色吊带高腰A字公主裙","commodityPhoto":"/image/commodity/gongzhuqun.png","univalence":199,"totalprices":199},{"id":84,"commodityId":3,"count":5,"userId":95,"commodityName":"良品铺子麻辣鸭脖","commodityInfo":"良品铺子鸭脖甜辣鸭脖零食小吃休闲食品锁鲜小包装麻辣小零食解馋","commodityPhoto":"/image/commodity/yabo.png","univalence":25.5,"totalprices":127.5},{"id":85,"commodityId":3,"count":5,"userId":95,"commodityName":"良品铺子麻辣鸭脖","commodityInfo":"良品铺子鸭脖甜辣鸭脖零食小吃休闲食品锁鲜小包装麻辣小零食解馋","commodityPhoto":"/image/commodity/yabo.png","univalence":25.5,"totalprices":127.5},{"id":86,"commodityId":3,"count":5,"userId":95,"commodityName":"良品铺子麻辣鸭脖","commodityInfo":"良品铺子鸭脖甜辣鸭脖零食小吃休闲食品锁鲜小包装麻辣小零食解馋","commodityPhoto":"/image/commodity/yabo.png","univalence":25.5,"totalprices":127.5},{"id":138,"commodityId":3,"count":1,"userId":95,"commodityName":"良品铺子麻辣鸭脖","commodityInfo":"良品铺子鸭脖甜辣鸭脖零食小吃休闲食品锁鲜小包装麻辣小零食解馋","commodityPhoto":"/image/commodity/yabo.png","univalence":25.5,"totalprices":25.5}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 80
         * commodityId : 35
         * count : 1
         * userId : 95
         * commodityName : 公主裙
         * commodityInfo : 桃丝花园-浪漫印花花朵连衣裙花色吊带高腰A字公主裙
         * commodityPhoto : /image/commodity/gongzhuqun.png
         * univalence : 199
         * totalprices : 199
         */

        private int id;
        private int commodityId;
        private int count;
        private int userId;
        private String commodityName;
        private String commodityInfo;
        private String commodityPhoto;
        private double univalence;
        private double totalprices;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getCommodityInfo() {
            return commodityInfo;
        }

        public void setCommodityInfo(String commodityInfo) {
            this.commodityInfo = commodityInfo;
        }

        public String getCommodityPhoto() {
            return commodityPhoto;
        }

        public void setCommodityPhoto(String commodityPhoto) {
            this.commodityPhoto = commodityPhoto;
        }

        public double getUnivalence() {
            return univalence;
        }

        public void setUnivalence(double univalence) {
            this.univalence = univalence;
        }

        public double getTotalprices() {
            return totalprices;
        }

        public void setTotalprices(double totalprices) {
            this.totalprices = totalprices;
        }
    }
}
