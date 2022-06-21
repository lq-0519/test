//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package lq.test;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SquareActivityQuery implements Serializable {
    private static final long serialVersionUID = -1609743323721361447L;
    private String pin;
    private String loginPin;
    private Integer cardPinType;
    private Integer queryType;
    private List<String> marketSceneTags;
    private List<String> activityTypeTags;
    private String cardActivityName;
    private String cardActivityId;
    private Date minActivityBeginTime;
    private Date maxActivityBeginTime;
    private String orderBy;

    public SquareActivityQuery() {
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getLoginPin() {
        return this.loginPin;
    }

    public void setLoginPin(String loginPin) {
        this.loginPin = loginPin;
    }

    public Integer getCardPinType() {
        return this.cardPinType;
    }

    public void setCardPinType(Integer cardPinType) {
        this.cardPinType = cardPinType;
    }

    public Integer getQueryType() {
        return this.queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public List<String> getMarketSceneTags() {
        return this.marketSceneTags;
    }

    public void setMarketSceneTags(List<String> marketSceneTags) {
        this.marketSceneTags = marketSceneTags;
    }

    public List<String> getActivityTypeTags() {
        return this.activityTypeTags;
    }

    public void setActivityTypeTags(List<String> activityTypeTags) {
        this.activityTypeTags = activityTypeTags;
    }

    public String getCardActivityName() {
        return this.cardActivityName;
    }

    public void setCardActivityName(String cardActivityName) {
        this.cardActivityName = cardActivityName;
    }

    public String getCardActivityId() {
        return this.cardActivityId;
    }

    public void setCardActivityId(String cardActivityId) {
        this.cardActivityId = cardActivityId;
    }

    public Date getMinActivityBeginTime() {
        return this.minActivityBeginTime;
    }

    public void setMinActivityBeginTime(Date minActivityBeginTime) {
        this.minActivityBeginTime = minActivityBeginTime;
    }

    public Date getMaxActivityBeginTime() {
        return this.maxActivityBeginTime;
    }

    public void setMaxActivityBeginTime(Date maxActivityBeginTime) {
        this.maxActivityBeginTime = maxActivityBeginTime;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "SquareActivityQuery{pin='" + this.pin + '\'' + ", loginPin='" + this.loginPin + '\'' + ", cardPinType=" + this.cardPinType + ", queryType=" + this.queryType + ", marketSceneTags=" + this.marketSceneTags + ", activityTypeTags=" + this.activityTypeTags + ", cardActivityName='" + this.cardActivityName + '\'' + ", cardActivityId='" + this.cardActivityId + '\'' + ", minActivityBeginTime=" + this.minActivityBeginTime + ", maxActivityBeginTime=" + this.maxActivityBeginTime + ", orderBy='" + this.orderBy + '\'' + '}';
    }
}
