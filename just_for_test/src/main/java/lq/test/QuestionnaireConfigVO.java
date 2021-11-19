package lq.test;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家端调研问卷信息配置信息
 *
 * @author liqian477
 * @date 2021/11/15 18:27
 */
public class QuestionnaireConfigVO implements Serializable {

    private static final long serialVersionUID = -1882100065756002016L;

    /**
     * 问卷ID
     */
    private Integer id;

    /**
     * 问卷名称
     */
    private String name;

    /**
     * 问卷位置，1：运营端，2：商家端
     */
    private Integer position;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 跳转链接
     */
    private String jumpUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    @Override
    public String toString() {
        return "QuestionnaireConfigVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", jumpUrl='" + jumpUrl + '\'' +
                '}';
    }
}
