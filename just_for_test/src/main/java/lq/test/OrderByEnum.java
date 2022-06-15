package lq.test;

import java.util.HashMap;
import java.util.Map;

/**
 * OrderByEnum
 *
 * @author liangduoze
 * @date 2022/1/27 15:22
 */
public enum OrderByEnum {

    /**
     * 活动开始时间升序
     */
    BEGIN_TIME_ASC("BEGIN_TIME_ASC", "活动开始时间升序", null, true),

    /**
     * 活动开始时时间降序
     */
    BEGIN_TIME_DESC("BEGIN_TIME_DESC", "活动开始时时间降序", null, false),

    /**
     * 最新活动资源时间升序
     */
    LAST_ASSIGN_TIME_ASC("LAST_ASSIGN_TIME_ASC", "最新活动资源时间升序", "lastAssignTime", true),

    /**
     * 最新活动资源时间降序
     */
    LAST_ASSIGN_TIME_DESC("LAST_ASSIGN_TIME_DESC", "最新活动资源时间降序", "lastAssignTime", false),

    /**
     * 资源广场-最新发布时间升序
     */
    CARD_PUBLISH_TIME_ASC("CARD_PUBLISH_TIME_ASC", "资源广场-最新发布时间升序", "cardPublishTime", true),

    /**
     * 资源广场-最新发布时间降序
     */
    CARD_PUBLISH_TIME_DESC("CARD_PUBLISH_TIME_DESC", "资源广场-最新发布时间降序", "cardPublishTime", false),

    /**
     * 资源广场-报名截止时间升序
     */
    CARD_APPLY_END_TIME_ASC("CARD_APPLY_END_TIME_ASC", "资源广场-报名截止时间升序", "cardApplyEndTime", true),

    /**
     * 资源广场-报名截止时间降序
     */
    CARD_APPLY_END_TIME_DESC("CARD_APPLY_END_TIME_DESC", "资源广场-报名截止时间降序", "cardApplyEndTime", false),

    //---------------------------------------------------------------------------------
    ;

    private static final Map<String, OrderByEnum> CODE_MAP_ENUMS = new HashMap<String, OrderByEnum>();

    static {
        OrderByEnum[] enums = OrderByEnum.values();
        for (OrderByEnum oneEnum : enums) {
            CODE_MAP_ENUMS.put(oneEnum.getField(), oneEnum);
        }
    }

    OrderByEnum(String field, String description, String sortField, boolean sortAsc) {
        this.field = field;
        this.description = description;
        this.sortField = sortField;
        this.sortAsc = sortAsc;
    }

    /**
     * parseOf with field
     */
    public static OrderByEnum parseOf(String field) {
        return CODE_MAP_ENUMS.get(field);
    }

    /**
     * isEquals with code
     */
    public boolean isEquals(String scene) {
        return this.field.equals(scene);
    }

    /**
     * 场景
     */
    private final String field;

    /**
     * 描述
     */
    private final String description;

    /**
     * 排序字段
     */
    private final String sortField;

    /**
     * 描述
     * true:升序
     */
    private final boolean sortAsc;

    public String getField() {
        return field;
    }

    public String getDescription() {
        return description;
    }

    public String getSortField() {
        return sortField;
    }

    public boolean isSortAsc() {
        return sortAsc;
    }

    @Override
    public String toString() {
        return "OrderByEnum{" +
                "field='" + field + '\'' +
                ", description='" + description + '\'' +
                ", sortField='" + sortField + '\'' +
                ", sortAsc=" + sortAsc +
                '}';
    }
}
