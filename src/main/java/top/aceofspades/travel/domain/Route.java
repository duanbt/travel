package top.aceofspades.travel.domain;

import lombok.Data;

import java.util.List;

/**
 * 旅游路线商品实体类
 *
 * @author duanbt
 * @version 1.0
 **/
@Data
public class Route {
    /**
     * 线路id
     */
    private int rid;
    /**
     * 线路名称
     */
    private String rname;
    /**
     * 价格
     */
    private double price;
    /**
     * 线路介绍
     */
    private String routeIntroduce;
    /**
     * 是否上架,0表示未上架，1表示上架
     */
    private String rflag;

    /**
     * 上架时间
     */
    private String rdate;

    /**
     * 是否主题旅游，0表示不是，1表示是
     */
    private String isThemeTour;

    /**
     * 收藏数量
     */
    private int count;

    /**
     * 所属分类
     */
    private int cid;

    /**
     * 缩略图
     */
    private String rimage;

    /**
     * 所属商家
     */
    private int sid;

    /**
     * 抓取数据的来源id
     */
    private String sourceId;

    /**
     * 所属分类
     */
    private Category category;
    /**
     * 所属商家
     */
    private Seller seller;

    /**
     * 商品详情图片列表
     */
    private List<RouteImg> routeImgList;
}
