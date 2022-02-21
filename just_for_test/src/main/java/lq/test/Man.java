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
                new MessageTemplateDTO("APPLY_PEND_CHECK", "http://mc.jd.com/dist/pages/activeManage/list/?tab=2&activityId={activityId}"),
                new MessageTemplateDTO("CHANGE_PEND_CHECK", "http://mc.jd.com/dist/pages/activeManage/list/?tab=2&activityId={activityId}"),
                new MessageTemplateDTO("PASS_APPLY", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0"),
                new MessageTemplateDTO("REJECT_APPLY", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0"),
                new MessageTemplateDTO("VIEW_CHECK_REJECT", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_APPLICANT", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0"),
                new MessageTemplateDTO("APPLY_MATERIAL_DELETED", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0"),
                new MessageTemplateDTO("CHECK_PRESS", "http://mc.jd.com/dist/pages/activeManage/list/?tab=2&activityId={activityId}"),
                new MessageTemplateDTO("SYSTEM_AUTO_CHECK_REJECT", "http://mc.jd.com/dist/pages/activeManage/list/?tab=2&activityId={activityId}"),
                new MessageTemplateDTO("YX_ACTIVITY_APPLY", "http://test1.hd.jd.com/activity-manage/merchants/venueDetail/poolList?entry=1&planCode={hdPlanCode}&roomId={hdRoomId}&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("YX_REJECT_APPLY", "http://test1.hd.jd.com/activity-manage/merchants/venueDetail/registeredMaterials?activityId={activityId}&tab=1&blockId={areaId}&isBookModule=0&planCode={hdPlanCode}&roomId={hdRoomId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR_2_YX", "https://hd.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED_2_YX", "https://hd.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=2&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore"),
                new MessageTemplateDTO("APPLY_PRESS_2_YX", "https://hd.jd.com/activity-manage/merchants/venueDetail/poolList?entry=1&planCode={hdPlanCode}&roomId={hdRoomId}&activityId={activityId}&from=acore"),
                new MessageTemplateDTO("APPLY_VIEW_CHECK", "http://mc.jd.com/dist/pages/activeManage/list/?tab=1&activityId={activityId}"),
                new MessageTemplateDTO("INVITE_ACTIVITY_APPLY_CHANNEL", "http://test1.hd.jd.com/channel-service/activity/collection-pool-list/pools/roomlist?planCode={hdPlanCode}&entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR_CHANNEL", "http://test1.hd.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=0&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&from=acore"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED_CHANNEL", "http://test1.hd.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=0&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&from=acore"),
                new MessageTemplateDTO("APPLY_PRESS_CHANNEL", "http://test1.hd.jd.com/channel-service/activity/collection-pool-list/pools/roomlist?planCode={hdPlanCode}&entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK_CHANNEL", "http://mc.jd.com/dist/pages/activeResource/audit/?type=1&entry=1&activityId={activityId}&blockId={areaId}&batchId={batchId}"),
                new MessageTemplateDTO("INVITE_ACTIVITY_APPLY", "http://mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK", "http://mc.jd.com/dist/pages/activeResource/audit/?type=1&entry=1&activityId={activityId}&blockId={areaId}&batchId={batchId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR", "http://mc.jd.com/dist/pages/activeResource/audit/?type=1&entry=1&activityId={activityId}&blockId={areaId}&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED", "http://mc.jd.com/dist/pages/activeResource/audit/?type=2&entry=1&activityId={activityId}&blockId={areaId}&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS", "http://mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&blockId={areaId}"),
                null
        );

        ArrayList<MessageTemplateDTO> newTestList = Lists.newArrayList(
                new MessageTemplateDTO("APPLY_PEND_CHECK", "http://test1.yx.jd.com/all-about-me/audit-material/audit-for-me"),
                new MessageTemplateDTO("CHANGE_PEND_CHECK", "http://test1.yx.jd.com/all-about-me/audit-material/audit-for-me"),
                new MessageTemplateDTO("PASS_APPLY", "http://test1.yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyId}&isBookModule=0"),
                new MessageTemplateDTO("REJECT_APPLY", "http://test1.yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyId}&isBookModule=0"),
                new MessageTemplateDTO("VIEW_CHECK_REJECT", "http://test1.yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyId}&isBookModule=0"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_APPLICANT", "http://test1.yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyId}&isBookModule=0"),
                new MessageTemplateDTO("APPLY_MATERIAL_DELETED", "http://test1.yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyId}&isBookModule=0"),
                new MessageTemplateDTO("CHECK_PRESS", "http://test1.yx.jd.com/all-about-me/audit-material/audit-for-me"),
                new MessageTemplateDTO("SYSTEM_AUTO_CHECK_REJECT", "http://test1.yx.jd.com/all-about-me/audit-material/audit-for-me"),
                new MessageTemplateDTO("YX_ACTIVITY_APPLY", "http://test1.yx.jd.com/activity-manage/merchants/venueDetail/poolList?entry=1&planCode={hdPlanCode}&roomId={hdRoomId}&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("YX_REJECT_APPLY", "http://test1.yx.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR_2_YX", "http://test1.yx.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED_2_YX", "http://test1.yx.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS_2_YX", "http://test1.yx.jd.com/activity-manage/merchants/venueDetail/poolList?entry=1&planCode={hdPlanCode}&roomId={hdRoomId}&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("APPLY_VIEW_CHECK", "http://test1.yx.jd.com/all-about-me/audit-material/audit-for-me"),
                new MessageTemplateDTO("INVITE_ACTIVITY_APPLY_CHANNEL", "http://test1.yx.jd.com/channel-service/activity/collection-pool-list/pools/roomlist?planCode={hdPlanCode}&entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR_CHANNEL", "http://test1.yx.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED_CHANNEL", "http://test1.yx.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS_CHANNEL", "http://test1.yx.jd.com/channel-service/activity/collection-pool-list/pools/roomlist?planCode={hdPlanCode}&entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK_CHANNEL", "http://test1.yx.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("INVITE_ACTIVITY_APPLY", "http://test1.yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK", "http://test1.yx.jd.com/activity-manage/myCreate/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR", "http://test1.yx.jd.com/activity-manage/myCreate/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED", "http://test1.yx.jd.com/activity-manage/myCreate/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS", "http://test1.yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                null
        );

        ArrayList<MessageTemplateDTO> oldProdList = Lists.newArrayList(
                new MessageTemplateDTO("APPLY_PEND_CHECK", "http://mc.jd.com/dist/pages/activeManage/list/?tab=2&activityId={activityId}"),
                new MessageTemplateDTO("CHANGE_PEND_CHECK", "http://mc.jd.com/dist/pages/activeManage/list/?tab=2&activityId={activityId}"),
                new MessageTemplateDTO("PASS_APPLY", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0"),
                new MessageTemplateDTO("REJECT_APPLY", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1"),
                new MessageTemplateDTO("VIEW_CHECK_REJECT", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_APPLICANT", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0"),
                new MessageTemplateDTO("APPLY_MATERIAL_DELETED", "http://mc.jd.com/dist/pages/activeEnroll/list/?tab=1&applyId={applyId}&isBookModule=0"),
                new MessageTemplateDTO("CHECK_PRESS", "http://mc.jd.com/dist/pages/activeManage/list/?tab=2&activityId={activityId}"),
                new MessageTemplateDTO("SYSTEM_AUTO_CHECK_REJECT", "http://mc.jd.com/dist/pages/activeManage/list/?tab=2&activityId={activityId}"),
                new MessageTemplateDTO("YX_ACTIVITY_APPLY", "http://hd.jd.com/activity-manage/merchants/venueDetail/poolList?entry=1&planCode={hdPlanCode}&roomId={hdRoomId}&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("YX_REJECT_APPLY", "http://hd.jd.com/activity-manage/merchants/venueDetail/registeredMaterials?activityId={activityId}&tab=1&blockId={areaId}&isBookModule=0&planCode={hdPlanCode}&roomId={hdRoomId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR_2_YX", "https://hd.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED_2_YX", "https://hd.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=2&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore"),
                new MessageTemplateDTO("APPLY_PRESS_2_YX", "https://hd.jd.com/activity-manage/merchants/venueDetail/poolList?entry=1&planCode={hdPlanCode}&roomId={hdRoomId}&activityId={activityId}&from=acore"),
                new MessageTemplateDTO("APPLY_VIEW_CHECK", "http://mc.jd.com/dist/pages/activeManage/list/?tab=1&activityId={activityId}"),
                new MessageTemplateDTO("INVITE_ACTIVITY_APPLY_CHANNEL", "https://hd.jd.com/channel-service/activity/collection-pool-list/pools/roomlist?planCode={hdPlanCode}&entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR_CHANNEL", "https://hd.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=0&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&from=acore"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED_CHANNEL", "https://hd.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=0&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&from=acore"),
                new MessageTemplateDTO("APPLY_PRESS_CHANNEL", "https://hd.jd.com/channel-service/activity/collection-pool-list/pools/roomlist?planCode={hdPlanCode}&entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK_CHANNEL", "http://mc.jd.com/dist/pages/activeResource/audit/?type=1&entry=1&activityId={activityId}&blockId={areaId}&batchId={batchId}"),
                new MessageTemplateDTO("INVITE_ACTIVITY_APPLY", "http://mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK", "http://mc.jd.com/dist/pages/activeResource/audit/?type=1&entry=1&activityId={activityId}&blockId={areaId}&batchId={batchId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR", "http://mc.jd.com/dist/pages/activeResource/audit/?type=1&entry=1&activityId={activityId}&blockId={areaId}&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED", "http://mc.jd.com/dist/pages/activeResource/audit/?type=2&entry=1&activityId={activityId}&blockId={areaId}&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS", "http://mc.jd.com/dist/pages/activeEnrollBlock/?entry=1&activityId={activityId}&blockId={areaId}"),
                null
        );

        ArrayList<MessageTemplateDTO> newProdList = Lists.newArrayList(
                new MessageTemplateDTO("APPLY_PEND_CHECK", "http://yx.jd.com/all-about-me/audit-material/audit-for-me"),
                new MessageTemplateDTO("CHANGE_PEND_CHECK", "http://yx.jd.com/all-about-me/audit-material/audit-for-me"),
                new MessageTemplateDTO("PASS_APPLY", "https://yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyId}&isBookModule=0"),
                new MessageTemplateDTO("REJECT_APPLY", "https://yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyId}&isBookModule=0"),
                new MessageTemplateDTO("VIEW_CHECK_REJECT", "https://yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyId}&isBookModule=0"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_APPLICANT", "https://yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyId}&isBookModule=0"),
                new MessageTemplateDTO("APPLY_MATERIAL_DELETED", "https://yx.jd.com/all-about-me/signed-material/sign?tab=1&applyid={applyId}&isBookModule=0"),
                new MessageTemplateDTO("CHECK_PRESS", "http://yx.jd.com/all-about-me/audit-material/audit-for-me"),
                new MessageTemplateDTO("SYSTEM_AUTO_CHECK_REJECT", "http://yx.jd.com/all-about-me/audit-material/audit-for-me"),
                new MessageTemplateDTO("YX_ACTIVITY_APPLY", "https://yx.jd.com/activity-manage/merchants/venueDetail/poolList?entry=1&planCode={hdPlanCode}&roomId={hdRoomId}&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("YX_REJECT_APPLY", "https://yx.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR_2_YX", "https://yx.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED_2_YX", "https://yx.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS_2_YX", "https://yx.jd.com/activity-manage/merchants/venueDetail/poolList?entry=1&planCode={hdPlanCode}&roomId={hdRoomId}&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("APPLY_VIEW_CHECK", "http://yx.jd.com/all-about-me/audit-material/audit-for-me"),
                new MessageTemplateDTO("INVITE_ACTIVITY_APPLY_CHANNEL", "https://yx.jd.com/channel-service/activity/collection-pool-list/pools/roomlist?planCode={hdPlanCode}&entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR_CHANNEL", "https://yx.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED_CHANNEL", "https://yx.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS_CHANNEL", "https://yx.jd.com/channel-service/activity/collection-pool-list/pools/roomlist?planCode={hdPlanCode}&entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK_CHANNEL", "https://yx.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("INVITE_ACTIVITY_APPLY", "https://yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK", "https://yx.jd.com/activity-manage/myCreate/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR", "https://yx.jd.com/activity-manage/myCreate/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED", "https://yx.jd.com/activity-manage/myCreate/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS", "https://yx.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                null
        );
        System.out.println("oldTestList = " + JSON.toJSONString(oldTestList));
        System.out.println("newTestList = " + JSON.toJSONString(newTestList));
        System.out.println("oldProdList = " + JSON.toJSONString(oldProdList));
        System.out.println("newProdList = " + JSON.toJSONString(newProdList));
    }
}
