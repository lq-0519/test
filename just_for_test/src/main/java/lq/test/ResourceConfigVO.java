package lq.test;

import java.util.Date;

public class ResourceConfigVO {


    /**
     * 主键
     */
    private Long id;

    /**
     * 主题活动 id
     */
    private String planCode;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 版本号
     */
    private Integer activityVersion;

    /**
     * 活动开始时间
     */
    private Date activityStartTime;

    /**
     * 活动结束时间
     */
    private Date activityEndTime;

    /**
     * 图片链接
     */
    private String imageUrl;

    /**
     * 活动标题
     */
    private String activityTitle;

    /**
     * 开启状态，0：关闭，1：开启
     */
    private Integer status;

    /**
     * 展示起始时间
     */
    private Date startTime;

    /**
     * 展示结束时间
     */
    private Date endTime;


    /**
     * 招商开始时间
     */
    private Date businessStartTime;

    /**
     * 招商结束时间
     */
    private Date businessEndTime;
}