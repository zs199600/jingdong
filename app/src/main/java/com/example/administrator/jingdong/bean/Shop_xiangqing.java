package com.example.administrator.jingdong.bean;

/**
 * Created by Administrator on 2018/2/7.
 */

public class Shop_xiangqing {

    /**
     * msg :
     * seller : {"description":"我是商家21","icon":"http://120.27.23.105/images/icon.png","name":"商家21","productNums":999,"score":5,"sellerid":21}
     * code : 0
     * data : {"bargainPrice":111.99,"createtime":"2017-10-14T21:39:05","detailUrl":"https://item.m.jd.com/product/4719303.html?utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=QQfriends","images":"https://m.360buyimg.com/n0/jfs/t9004/210/1160833155/647627/ad6be059/59b4f4e1N9a2b1532.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t7504/338/63721388/491286/f5957f53/598e95f1N7f2adb87.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t7441/10/64242474/419246/adb30a7d/598e95fbNd989ba0a.jpg!q70.jpg","itemtype":1,"pid":5,"price":88.99,"pscid":1,"salenum":678,"sellerid":21,"subhead":"每个中秋都不能简单，无论身在何处，你总需要一块饼让生活更圆满，京东月饼让爱更圆满京东自营，闪电配送，更多惊喜，快用手指戳一下","title":"北京稻香村 稻香村中秋节月饼 老北京月饼礼盒655g"}
     */

    private String msg;
    private SellerBean seller;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SellerBean getSeller() {
        return seller;
    }

    public void setSeller(SellerBean seller) {
        this.seller = seller;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class SellerBean {
        /**
         * description : 我是商家21
         * icon : http://120.27.23.105/images/icon.png
         * name : 商家21
         * productNums : 999
         * score : 5
         * sellerid : 21
         */

        private String description;
        private String icon;
        private String name;
        private double productNums;
        private double score;
        private double sellerid;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getProductNums() {
            return productNums;
        }

        public void setProductNums(double productNums) {
            this.productNums = productNums;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public double getSellerid() {
            return sellerid;
        }

        public void setSellerid(double sellerid) {
            this.sellerid = sellerid;
        }
    }

    public static class DataBean {
        /**
         * bargainPrice : 111.99
         * createtime : 2017-10-14T21:39:05
         * detailUrl : https://item.m.jd.com/product/4719303.html?utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=QQfriends
         * images : https://m.360buyimg.com/n0/jfs/t9004/210/1160833155/647627/ad6be059/59b4f4e1N9a2b1532.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t7504/338/63721388/491286/f5957f53/598e95f1N7f2adb87.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t7441/10/64242474/419246/adb30a7d/598e95fbNd989ba0a.jpg!q70.jpg
         * itemtype : 1
         * pid : 5
         * price : 88.99
         * pscid : 1
         * salenum : 678
         * sellerid : 21
         * subhead : 每个中秋都不能简单，无论身在何处，你总需要一块饼让生活更圆满，京东月饼让爱更圆满京东自营，闪电配送，更多惊喜，快用手指戳一下
         * title : 北京稻香村 稻香村中秋节月饼 老北京月饼礼盒655g
         */

        private double bargainPrice;
        private String createtime;
        private String detailUrl;
        private String images;
        private double itemtype;
        private double pid;
        private double price;
        private double pscid;
        private double salenum;
        private double sellerid;
        private String subhead;
        private String title;

        public double getBargainPrice() {
            return bargainPrice;
        }

        public void setBargainPrice(double bargainPrice) {
            this.bargainPrice = bargainPrice;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public double getItemtype() {
            return itemtype;
        }

        public void setItemtype(double itemtype) {
            this.itemtype = itemtype;
        }

        public double getPid() {
            return pid;
        }

        public void setPid(double pid) {
            this.pid = pid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getPscid() {
            return pscid;
        }

        public void setPscid(double pscid) {
            this.pscid = pscid;
        }

        public double getSalenum() {
            return salenum;
        }

        public void setSalenum(double salenum) {
            this.salenum = salenum;
        }

        public double getSellerid() {
            return sellerid;
        }

        public void setSellerid(double sellerid) {
            this.sellerid = sellerid;
        }

        public String getSubhead() {
            return subhead;
        }

        public void setSubhead(String subhead) {
            this.subhead = subhead;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
