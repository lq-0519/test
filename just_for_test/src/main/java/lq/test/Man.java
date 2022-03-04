package lq.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liqian477
 * @date 2021/8/2 17:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Man implements Serializable {

    private static final long serialVersionUID = -7240596588043512147L;

    private Integer age;

    private String name;

    private List<Man> manList;

    public Man(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        getMessageJsonStr();
    }

    public static void getMessageJsonStr() {
        ArrayList<MessageTemplateDTO> oldTestList = Lists.newArrayList(
                new MessageTemplateDTO("ACTIVITY_APPLY", "http://test.mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("RESOURCE_UPDATE", "http://test.mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("CROSS_BATCH_ADJUST", "http://test.mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0"),
                new MessageTemplateDTO("COLL_ACTIVITY_PRE_PASS", "http://test.mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}"),
                new MessageTemplateDTO("COLL_ACTIVITY_PRE_REJECT", "http://test.mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}"),
                new MessageTemplateDTO("COLL_ACTIVITY_FORMAL_RECO", "http://test.mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&tabName=saima"),
                new MessageTemplateDTO("MATERIAL_LIB_ACTIVITY_APPLY", "http://test.mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&tabName=saima"),
                new MessageTemplateDTO("MATERIAL_LIB_INVITE_ACTIVITY_APPLY", "http://test.mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&tabName=saima"),
                new MessageTemplateDTO("COLL_ACTIVITY_PRE_BID_SUCCESS", "http://test.mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("MATERIAL_SKU_OFFLINE_SEND_APPLICANT", "http://test.mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0"),
                new MessageTemplateDTO("PRICE_SECONDARY_APPLY", "http://test.mc.jd.com/dist/pages/activeEnroll/list/?tab=1&blockId={areaId}&isBookModule=0"),
                new MessageTemplateDTO("CHARGE_MODE_CHECK_PASS", "http://test.mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0&filterTab=2"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK_TO_OPERA", "http://test.mc.jd.com"),
                new MessageTemplateDTO("COMPARE_PRICE_CHECK", "http://test.mc.jd.com/dist/pages/"),
                new MessageTemplateDTO("COMPARE_PRICE_CHECK_UNDER", "http://test.mc.jd.com/dist/pages/"),
                new MessageTemplateDTO("PROMO_FAIL_APPLY", "http://test.mc.jd.com/dist/pages/activeEnroll/list/?tab=1&blockId={areaId}&isBookModule=0"),
                new MessageTemplateDTO("PROMO_FAIL_CHECKER", "http://test.mc.jd.com/dist/pages/activeResource/audit/?entry=1&activityId={activityId}&blockId={areaId}&isViewCheckPass=&isChangeCheck=0&type=1"),
                new MessageTemplateDTO("CMS_MONETIZATION_FAILURE_APPLY", "http://test.mc.jd.com/dist/pages/activeEnroll/list/?tab=1&blockId={areaId}&isBookModule=0"),
                new MessageTemplateDTO("测试", "http://test.mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}"),
                null
        );

        ArrayList<MessageTemplateDTO> newTestList = Lists.newArrayList(
                new MessageTemplateDTO("ACTIVITY_APPLY", "http://test1.yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("RESOURCE_UPDATE", "http://test1.yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("CROSS_BATCH_ADJUST", "http://test1.yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyID}&isBookModule=0"),
                new MessageTemplateDTO("COLL_ACTIVITY_PRE_PASS", "http://test1.yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("COLL_ACTIVITY_PRE_REJECT", "http://test1.yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("COLL_ACTIVITY_FORMAL_RECO", "http://test1.yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("MATERIAL_LIB_ACTIVITY_APPLY", "http://test1.yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("MATERIAL_LIB_INVITE_ACTIVITY_APPLY", "http://test1.yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("COLL_ACTIVITY_PRE_BID_SUCCESS", "http://test1.yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("MATERIAL_SKU_OFFLINE_SEND_APPLICANT", "http://test1.yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyID}&isBookModule=0"),
                new MessageTemplateDTO("PRICE_SECONDARY_APPLY", "http://test1.yx.jd.com/all-about-me/signed-material/sign?tab=1&blockId={areaId}&isBookModule=0"),
                new MessageTemplateDTO("CHARGE_MODE_CHECK_PASS", "http://test1.yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyID}&isBookModule=0"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK_TO_OPERA", "http://test1.yx.jd.com/"),
                new MessageTemplateDTO("COMPARE_PRICE_CHECK", "http://test1.yx.jd.com/"),
                new MessageTemplateDTO("COMPARE_PRICE_CHECK_UNDER", "http://test1.yx.jd.com/"),
                new MessageTemplateDTO("PROMO_FAIL_APPLY", "http://test1.yx.jd.com/all-about-me/signed-material/sign?tab=1&blockId={areaId}&isBookModule=0/"),
                new MessageTemplateDTO("PROMO_FAIL_CHECKER", "http://yx.jd.com/all-about-me/audit-material/audit-for-me"),
                new MessageTemplateDTO("CMS_MONETIZATION_FAILURE_APPLY", "http://test1.yx.jd.com/all-about-me/signed-material/sign?tab=1&blockId={areaId}&isBookModule=0"),
                new MessageTemplateDTO("测试", "http://test1.yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyID}&isBookModule=0"),
                null
        );

        ArrayList<MessageTemplateDTO> oldProdList = Lists.newArrayList(
                new MessageTemplateDTO("ACTIVITY_APPLY", "http://mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("RESOURCE_UPDATE", "http://mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("CROSS_BATCH_ADJUST", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0"),
                new MessageTemplateDTO("COLL_ACTIVITY_PRE_PASS", "http://mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}"),
                new MessageTemplateDTO("COLL_ACTIVITY_PRE_REJECT", "http://mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}"),
                new MessageTemplateDTO("COLL_ACTIVITY_FORMAL_RECO", "http://mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&tabName=saima"),
                new MessageTemplateDTO("MATERIAL_LIB_ACTIVITY_APPLY", "http://mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&tabName=saima"),
                new MessageTemplateDTO("MATERIAL_LIB_INVITE_ACTIVITY_APPLY", "http://mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&tabName=saima"),
                new MessageTemplateDTO("COLL_ACTIVITY_PRE_BID_SUCCESS", "http://mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("MATERIAL_SKU_OFFLINE_SEND_APPLICANT", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0"),
                new MessageTemplateDTO("PRICE_SECONDARY_APPLY", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&blockId={areaId}&isBookModule=0"),
                new MessageTemplateDTO("CHARGE_MODE_CHECK_PASS", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0&filterTab=2"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK_TO_OPERA", "http://mc.jd.com"),
                new MessageTemplateDTO("COMPARE_PRICE_CHECK", "https://mc.jd.com/dist/pages/"),
                new MessageTemplateDTO("COMPARE_PRICE_CHECK_UNDER", "https://mc.jd.com/dist/pages/"),
                new MessageTemplateDTO("PROMO_FAIL_APPLY", "https://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&blockId={areaId}&isBookModule=0"),
                new MessageTemplateDTO("PROMO_FAIL_CHECKER", "https://mc.jd.com/dist/pages/activeResource/audit/?entry=1&activityId={activityId}&blockId={areaId}&isViewCheckPass=&isChangeCheck=0&type=1"),
                new MessageTemplateDTO("CMS_MONETIZATION_FAILURE_APPLY", "https://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&blockId={areaId}&isBookModule=0"),
                new MessageTemplateDTO("测试", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}"),
                null
        );

        ArrayList<MessageTemplateDTO> newProdList = Lists.newArrayList(
                new MessageTemplateDTO("ACTIVITY_APPLY", "https://yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("RESOURCE_UPDATE", "https://yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("CROSS_BATCH_ADJUST", "https://yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyID}&isBookModule=0"),
                new MessageTemplateDTO("COLL_ACTIVITY_PRE_PASS", "https://yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("COLL_ACTIVITY_PRE_REJECT", "https://yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("COLL_ACTIVITY_FORMAL_RECO", "https://yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("MATERIAL_LIB_ACTIVITY_APPLY", "https://yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("MATERIAL_LIB_INVITE_ACTIVITY_APPLY", "https://yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("COLL_ACTIVITY_PRE_BID_SUCCESS", "https://yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("MATERIAL_SKU_OFFLINE_SEND_APPLICANT", "https://yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyID}&isBookModule=0"),
                new MessageTemplateDTO("PRICE_SECONDARY_APPLY", "https://yx.jd.com/all-about-me/signed-material/sign?tab=1&blockId={areaId}&isBookModule=0"),
                new MessageTemplateDTO("CHARGE_MODE_CHECK_PASS", "https://yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyID}&isBookModule=0"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK_TO_OPERA", "https://yx.jd.com/"),
                new MessageTemplateDTO("COMPARE_PRICE_CHECK", "https://yx.jd.com/"),
                new MessageTemplateDTO("COMPARE_PRICE_CHECK_UNDER", "https://yx.jd.com/"),
                new MessageTemplateDTO("PROMO_FAIL_APPLY", "https://yx.jd.com/all-about-me/signed-material/sign?tab=1&blockId={areaId}&isBookModule=0/"),
                new MessageTemplateDTO("PROMO_FAIL_CHECKER", "http://yx.jd.com/all-about-me/audit-material/audit-for-me"),
                new MessageTemplateDTO("CMS_MONETIZATION_FAILURE_APPLY", "https://yx.jd.com/all-about-me/signed-material/sign?tab=1&blockId={areaId}&isBookModule=0"),
                new MessageTemplateDTO("测试", "https://yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyID}&isBookModule=0"),
                null
        );
        System.out.println("oldTestList = " + JSON.toJSONString(oldTestList));
        System.out.println("newTestList = " + JSON.toJSONString(newTestList));
        System.out.println("oldProdList = " + JSON.toJSONString(oldProdList));
        System.out.println("newProdList = " + JSON.toJSONString(newProdList));
    }
}
