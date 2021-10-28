package lq.test;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author liqian477
 */
@Data
public class SyncAreaManagersAuth implements Serializable {

    private static final long serialVersionUID = 5415106533189429428L;

    /**
     * 收品池ID
     */
    private Long areaId;

    /**
     * 会场活动ID
     */
    private Long activityId;

    /**
     * 移除收品池管理员集合
     */
    private Set<String> delSet;

    /**
     * 添加收品池管理员集合
     */
    private Set<String> addSet;

}
