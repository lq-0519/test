package lq.test;


import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * ActivityQueryInfo
 *
 * @author fujiayan
 * @date 2021/08/03/18:08
 */
@Data
public class ActivityQueryInfo implements Serializable {

    private static final long serialVersionUID = 4269812772881436776L;
    /**
     * valid sort string list
     */
    private final static List<String> VALID_SORT_STRING_LIST = Arrays.asList("activityBeginTime ASC", "activityBeginTime DESC", "applyEndTime ASC", "applyEndTime DESC");
    /**
     * 资源位id
     */
    private Long outActivityId;
    /**
     * 排序字段
     */
    private String orderBy;
    /**
     * 最小报名开始时间
     */
    private Date minApplyBeginTime;
    /**
     * 最大报名结束时间
     */
    private Date maxApplyEndTime;
    /**
     * 页码
     */
    private Integer page;

    //-----------------------以上参数为请求入参--------------------
    /**
     * 页容
     */
    private Integer pageSize;
    /**
     * 排序字段
     */
    private String sortField;
    /**
     * 排序方式
     */
    private boolean sortAsc = true;
    /**
     * 活动id
     */
    private Long activityId;
    /**
     * 活动类型
     */
    private Integer activityType;
    /**
     * erp
     */
    private String pin;

}
