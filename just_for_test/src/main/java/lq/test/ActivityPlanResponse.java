package lq.test;

import java.util.Date;

public class ActivityPlanResponse {
    /**
     * 活动code
     */
    private String planCode;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 部门
     */
    private String organization;
    /**
     * 活动开始时间
     */
    private Date activityStartTime;
    /**
     * 活动结束时间
     */
    private Date activityEndTime;
    /**
     * 预热开始时间
     */
    private Date preStartTime;
    /**
     * 预热结束时间
     */
    private Date preEndTime;
    /**
     * 专场开始时间
     */
    private Date specialStartTime;
    /**
     * 专场结束时间
     */
    private Date specialEndTime;
    /**
     * 高潮开始时间
     */
    private Date highStartTime;
    /**
     * 高潮结束时间
     */
    private Date highEndTime;
    /**
     * 返场开始时间
     */
    private Date backStartTime;
    /**
     * 返场结束时间
     */
    private Date backEndTime;
    /**
     * 主推品类/品牌
     */
    private String category;
    /**
     * 活动联系人
     */
    private String contacts;
    /**
     * 活动级别
     */
    private String level;
    /**
     * 其他活动级别
     */
    private String otherLevel;
    /**
     * 创建人（废弃，兼容1.0.1版本）
     */
    private String createErp;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态, 0：无效 1：有效 2:草稿
     */
    private Integer status;
    /**
     * 主推类目名称
     */
    private String categoryName;
    /**
     * 主商品池id
     */
    private String mainPoolId;
    /**
     * 基础商品池id
     */
    private String basePoolId;

    private String relationType;

    /**
     *
     */
    private String value;
    /**
     * 活动介绍
     */
    private String remark;

    /**
     * 活动计划创建人
     */
    private String activityCreateErp;

    /**
     * 商品池创建人
     */
    private String poolCreateErp;

    /**
     * 规则链接
     */
    private String ruleUrl;

    /**
     * 是否开启大促商品池 0:未开启， 1：已开启
     */
    private Integer salePool;
    /**
     * 店铺池id
     */
    private String shopPoolId;

    /**
     * 店铺池池创建人
     */
    private String shopPoolCreateErp;

    /**
     * 活动评级名称
     */
    private String levelName;

    /**
     * 缓存版本号
     */
    private Integer cacheVersion;

    /**
     * 0: 营销活动，1：频道活动
     */
    private Integer type;

    /**
     * 频道id
     */
    private Integer channelId;
    /**
     * 0：传统主题活动 1：新版本招商活动
     */
    private Integer version;

    /**
     * 招商开始时间
     */
    private Date businessStartTime;

    /**
     * 招商结束时间
     */
    private Date businessEndTime;

}