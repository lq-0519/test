package lq.test;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.jd.marketing.activity.common.tool.BeanConverter;
import lq.test.inner.bean.BeanSource;
import lq.test.inner.bean.BeanTarget;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.rocketmq.common.ThreadFactoryImpl;
import org.msgpack.MessagePack;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings({"ConstantConditions", "AlibabaAvoidManuallyCreateThread", "unused"})
public class Test {

    /**
     * 创建测试用线程池
     */
    private static final ExecutorService POOL_EXECUTOR = new ThreadPoolExecutor(
            5,
            10,
            0, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(20),
            new ThreadFactoryImpl("test"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private static final ThreadLocal<Man> THREAD_LOCAL = new ThreadLocal<>();
    private static int bucketSize = 0;
    private static final LinkedList<Integer> integers = new LinkedList<>();

    static Random random = new Random(0);

    List<String> stringList = new ArrayList();

    List<Integer> integerList = new ArrayList();

    public static void main(String[] args) throws Exception {
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
                new MessageTemplateDTO("APPLY_PEND_CHECK", "123"),
                new MessageTemplateDTO("CHANGE_PEND_CHECK", "123"),
                new MessageTemplateDTO("PASS_APPLY", "123"),
                new MessageTemplateDTO("REJECT_APPLY", "123"),
                new MessageTemplateDTO("VIEW_CHECK_REJECT", "123"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_APPLICANT", "123"),
                new MessageTemplateDTO("APPLY_MATERIAL_DELETED", "123"),
                new MessageTemplateDTO("CHECK_PRESS", "123"),
                new MessageTemplateDTO("SYSTEM_AUTO_CHECK_REJECT", "123"),
                new MessageTemplateDTO("YX_ACTIVITY_APPLY", "http://test1.hd.jd.com/activity-manage/merchants/venueDetail/poolList?entry=1&planCode={hdPlanCode}&roomId={hdRoomId}&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("YX_REJECT_APPLY", "http://test1.hd.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR_2_YX", "http://test1.hd.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED_2_YX", "http://test1.hd.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS_2_YX", "http://test1.hd.jd.com/activity-manage/merchants/venueDetail/poolList?entry=1&planCode={hdPlanCode}&roomId={hdRoomId}&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("APPLY_VIEW_CHECK", "111"),
                new MessageTemplateDTO("INVITE_ACTIVITY_APPLY_CHANNEL", "http://test1.hd.jd.com/channel-service/activity/collection-pool-list/pools/roomlist?planCode={plancode}&entry=1&activityId={activityID}&from=acore&hideTab=1&id={activityID}&blockId={areaId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR_CHANNEL", "http://test1.hd.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={plancode}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED_CHANNEL", "http://test1.hd.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={plancode}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS_CHANNEL", "http://test1.hd.jd.com/channel-service/activity/collection-pool-list/pools/roomlist?planCode={plancode}&entry=1&activityId={activityID}&from=acore&hideTab=1&id={activityID}&blockId={areaId}"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK_CHANNEL", "http://test1.hd.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={plancode}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("INVITE_ACTIVITY_APPLY", "http://test1.hd.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK", "http://test1.hd.jd.com/activity-manage/myCreate/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR", "http://test1.hd.jd.com/activity-manage/myCreate/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED", "http://test1.hd.jd.com/activity-manage/myCreate/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS", "http://test1.hd.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
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
                new MessageTemplateDTO("APPLY_PEND_CHECK", "123"),
                new MessageTemplateDTO("CHANGE_PEND_CHECK", "123"),
                new MessageTemplateDTO("PASS_APPLY", "123"),
                new MessageTemplateDTO("REJECT_APPLY", "123"),
                new MessageTemplateDTO("VIEW_CHECK_REJECT", "123"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_APPLICANT", "123"),
                new MessageTemplateDTO("APPLY_MATERIAL_DELETED", "123"),
                new MessageTemplateDTO("CHECK_PRESS", "123"),
                new MessageTemplateDTO("SYSTEM_AUTO_CHECK_REJECT", "123"),
                new MessageTemplateDTO("YX_ACTIVITY_APPLY", "https://hd.jd.com/activity-manage/merchants/venueDetail/poolList?entry=1&planCode={hdPlanCode}&roomId={hdRoomId}&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("YX_REJECT_APPLY", "https://hd.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR_2_YX", "https://hd.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED_2_YX", "https://hd.jd.com/activity-manage/merchants/venueDetail/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={hdPlanCode}&roomId={hdRoomId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS_2_YX", "https://hd.jd.com/activity-manage/merchants/venueDetail/poolList?entry=1&planCode={hdPlanCode}&roomId={hdRoomId}&activityId={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("APPLY_VIEW_CHECK", "111"),
                new MessageTemplateDTO("INVITE_ACTIVITY_APPLY_CHANNEL", "https://hd.jd.com/channel-service/activity/collection-pool-list/pools/roomlist?planCode={plancode}&entry=1&activityId={activityID}&from=acore&hideTab=1&id={activityID}&blockId={areaId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR_CHANNEL", "https://hd.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={plancode}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED_CHANNEL", "https://hd.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={plancode}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS_CHANNEL", "https://hd.jd.com/channel-service/activity/collection-pool-list/pools/roomlist?planCode={plancode}&entry=1&activityId={activityID}&from=acore&hideTab=1&id={activityID}&blockId={areaId}"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK_CHANNEL", "https://hd.jd.com/channel-service/activity/collection-pool-list/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&planCode={plancode}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("INVITE_ACTIVITY_APPLY", "https://hd.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                new MessageTemplateDTO("REJECT_MIDDLE_CHECK", "https://hd.jd.com/activity-manage/myCreate/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("MATERIAL_SYNC_FAIL_SEND_AUDITOR", "https://hd.jd.com/activity-manage/myCreate/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("CHECK_MATERIAL_DELETED", "https://hd.jd.com/activity-manage/myCreate/auditManagement?type=1&entry=1&activityId={activityId}&blockId={areaId}&from=acore&batchId={batchId}"),
                new MessageTemplateDTO("APPLY_PRESS", "https://hd.jd.com/activity-manage/myCreate/pools/roomlist?entry=1&activityId={activityId}&from=acore&hideTab=1&id={activityId}&blockId={areaId}"),
                null
        );
        System.out.println("oldTestList = " + JSON.toJSONString(oldTestList));
        System.out.println("newTestList = " + JSON.toJSONString(newTestList));
        System.out.println("oldProdList = " + JSON.toJSONString(oldProdList));
        System.out.println("newProdList = " + JSON.toJSONString(newProdList));
    }

    private static void m83() {
        Stream.of(new Man("1 "), new Man("2 "), new Man("3 "))
//                .peek(v -> v.setName(v.getName().trim()))
                .forEach(v -> System.out.println("v.getName() = " + v.getName()));
    }

    private static void m82() throws IOException, InterruptedException {
        System.in.read();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("i = " + (i + 1));
        }
    }

    private static void m81() {
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("all", Lists.newArrayList("CA010711284180664828", "CA120316170979717690", "CA120317112673179653", "CA101315302112013805", "CA101416223453986888"));
        map.put("self", Lists.newArrayList("CA120317081487587200", "CA120317112673179653"));
        map.put("pinGo", Lists.newArrayList("CA120317112653963450", "CA120317112628856525"));
        String s = JSON.toJSONString(map);
        System.out.println("s = " + s);
        @SuppressWarnings("unchecked") Map<String, List<String>> map1 = JSON.parseObject(s, Map.class);
        System.out.println("map1 = " + map1);
    }

    private static int m80() {
        int i = 0;
        try {
            i++;
            System.out.println("try");
            return i;
        } catch (Exception e) {
            i++;
            e.printStackTrace();
            System.out.println("catch");
            return i;
        } finally {
            i++;
            System.out.println("finally");
            return i;
        }
    }

    private static void m79() {
        Date date = new Date();
        long time = date.getTime();
        System.out.println("time = " + time);
    }

    private static void m78() {
        String x2 = new String("c") + new String("d"); // new String("cd")
        String x1 = "cd";
        String intern = x2.intern();
        System.out.println(intern == x1);
        System.out.println(x2 == x1);
    }

    private static void m77() {
        String s = "李乾";
        byte[] bytes = s.getBytes();
        for (byte aByte : bytes) {
            System.out.println("aByte = " + aByte);
        }
    }

    private static void m76() {
        Class<Man> manClass = Man.class;
        String canonicalName = manClass.getCanonicalName();
        String name = manClass.getName();
        String simpleName = manClass.getSimpleName();
        String typeName = manClass.getTypeName();
        System.out.println("typeName = " + typeName);
        System.out.println("simpleName = " + simpleName);
        System.out.println("name = " + name);
        System.out.println("canonicalName = " + canonicalName);
    }

    private static void m75() {
        try {
            User user = new User();
            user.setpName("source");

            ArrayList<User> users = new ArrayList<>();
            User user1 = new User();
            user1.setpName("1");
            users.add(user1);
            user.setUsers(users);

            User target = new User();
            BeanUtils.copyProperties(user, target);
            MessagePack messagePack = new MessagePack();
            byte[] raw = messagePack.write(target);
            User read = messagePack.read(raw, User.class);
            System.out.println(read);//输出：{"pId":9527,"pName":"华安","isMarry":true}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void m74() {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);
        objects.add(4);
        objects.add(5);
        objects.add(6);
        objects.add(7);
        List<Integer> integers = objects.subList(0, 2);
        System.out.println("integers = " + integers);
    }

    private static void m73() {
        BeanDouble beanDouble = new BeanDouble();
        beanDouble.setName("out");
        ArrayList<BeanDouble> beanDoubles = new ArrayList<>();
        beanDoubles.add(new BeanDouble("in 1", null));
        beanDoubles.add(new BeanDouble("in 2", null));
        beanDoubles.add(new BeanDouble("in 3", null));
        beanDouble.setBeanDoubles(beanDoubles);
        BeanDouble beanDouble1 = new BeanDouble();
        System.out.println("JSON.toJSONString(beanDouble) = " + JSON.toJSONString(beanDouble));
        BeanUtils.copyProperties(beanDouble, beanDouble1);
        System.out.println("JSON.toJSONString(beanDouble1) = " + JSON.toJSONString(beanDouble1));
    }

    private static void m72() throws InterruptedException {
        m69();
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("i111 = " + i);
        }
    }

    private static void m71() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println("1");
        ArrayList<Bean> beans = new ArrayList<>();
        while (true) {
            TimeUnit.MICROSECONDS.sleep(100);
            System.out.println(2);
            beans.add(new Bean());
        }
    }

    private static void m70() {
        ArrayList<Bean> beans = new ArrayList<>();
        while (true) {
            beans.add(new Bean());
        }
    }

    private static void m69() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i = " + i);
        }
    }

    private static void m68() {
        HashMap<String, String> stringStringHashMap = new HashMap<>(2);
        stringStringHashMap.put("1", "1");
        stringStringHashMap.put("2", "1");
        stringStringHashMap.put("3", "1");
        stringStringHashMap.put("4", "1");
        int size = stringStringHashMap.size();
        System.out.println("size = " + size);
    }

    private static void m67() {
        ArrayList<Long> objects = new ArrayList<>();
        objects.add(123L);
        objects.add(null);
        for (Long object : objects) {
            BigDecimal bigDecimal = BigDecimal.valueOf(object);
        }
    }

    private static void m66() {
        WhiteBackgroundRpcQuery whiteBackgroundRpcQuery = new WhiteBackgroundRpcQuery();
        whiteBackgroundRpcQuery.setTemplate_id("614c017f6915792f634a2976");
        ArrayList<WhiteBackgroundRpcQuery.JfsUrl> images = Lists.newArrayList(
                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/205279/23/16690/305142/61a8667aE913f78be/314cdcf9e5dd3766.png"),
                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/161380/29/28880/970398/61c46dddEc790a8c3/c1486924fdf06743.png"),
                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/222747/14/6962/279175/61c46e06E7690323b/1d5c51e3da568d15.png"),
                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/221678/5/6810/183276/61c46e07Eb3242d2d/07102de535c5f8a5.png"),
                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/135999/17/26944/88636/61c46e09Ed7579976/2831cef6dc4ebf39.png"),
                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/172506/11/25190/237239/61c46e0aEb8d35bcb/2169912618013e42.png"),
                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/220847/33/8718/93242/61c46e0eE626a8e6d/014bb5cbe633da7b.png"),
                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/172474/39/24783/145850/61c46e0fEe65b6303/9344c821e9c54db3.png"),
//                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/173519/7/24658/749383/61c46e7dE98b50467/a5ada2521ea80ac8.png"),
//                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/143453/24/24470/866492/61c46e7fE32fc9072/2bf1572afee07d18.png"),
//                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/170307/13/29207/303167/61c46e81E0f3ed254/e0b7d7804c232aec.png"),
//                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/15744/4/18301/183241/61c46e82Eed110894/4666861a441539f3.png"),
//                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/222616/32/6982/1004311/61c46e85E58890ad6/ff9dbef8504af17a.png"),
//                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/174762/34/23626/669295/61c46e87E5e4756f4/4dd49581d2491400.png"),
                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/209179/29/13684/823456/61c46e89Ecde0c522/601ae28762450b31.png"),
                new WhiteBackgroundRpcQuery.JfsUrl("jfs/t1/220397/26/8776/255819/61c46e8dE684258ad/7f29924a42dfdd67.png")
        );
        whiteBackgroundRpcQuery.setImages(images);
        whiteBackgroundRpcQuery.setNeed_matting(true);
        System.out.println(JSON.toJSONString(whiteBackgroundRpcQuery));

        List<String> collect = images.stream()
                .map(WhiteBackgroundRpcQuery.JfsUrl::getJfs_url)
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
        String join = Joiner.on(",").join(collect);
        System.out.println(join);

    }

    private static void m65() {
        String s = "1230123.1";
        System.out.println(NumberUtils.isNumber(s));
        System.out.println(NumberUtils.isDigits(s));
    }

    private static void m64() {
        String s = "{'erpNo':'linjiayu8'}";
//        String s = "";
        ActivityQueryInfo activityQueryInfo = JSON.parseObject(s, ActivityQueryInfo.class);
        System.out.println("JSON.toJSONString(activityQueryInfo) = " + JSON.toJSONString(activityQueryInfo));
    }

    private static void m63() {
        ArrayList<BeanSource> beanSources = new ArrayList<>();
        beanSources.add(new BeanSource("1", 1));
        beanSources.add(new BeanSource("2", 2));
        beanSources.add(new BeanSource("3", 3));
        System.out.println("JSON.toJSONString(beanSources) = " + JSON.toJSONString(beanSources));
        List<BeanTarget> beanTargets = BeanConverter.convertToList(BeanTarget.class, beanSources);
        System.out.println("JSON.toJSONString(beanTargets) = " + JSON.toJSONString(beanTargets));
    }

    private static void m62() {
        BeanSource beanSource = new BeanSource();
        beanSource.setName("s");
        System.out.println("JSON.toJSONString(beanSource) = " + JSON.toJSONString(beanSource));
        BeanTarget beanTarget = BeanConverter.convert(BeanTarget.class, beanSource);
        System.out.println("JSON.toJSONString(beanTarget) = " + JSON.toJSONString(beanTarget));
    }

    private static void m61() {
        ArrayList<BeanSource> beanSources = new ArrayList<>();
        beanSources.add(new BeanSource("source1", 1));
        beanSources.add(new BeanSource("source2", 2));
        beanSources.add(new BeanSource("source3", 3));
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                while (true) {
//                    try {
//                        TimeUnit.MICROSECONDS.sleep(5);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    List<BeanTarget> beanTargets = BeanConverter.convertToList(BeanTarget.class, beanSources);
//                    List<BeanTarget> beanTargets = BeanConverter.convertToListV2(BeanTarget.class, beanSources);
                }
            }).start();
        }
    }

    private static void m60() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(null);
        objects.add(new Object());
        objects.add(null);
        System.out.println("JSON.toJSONString(objects) = " + JSON.toJSONString(objects));
        List<Object> objects1 = BeanConverter.convertToList(Object.class, objects);
        System.out.println("JSON.toJSONString(objects1) = " + JSON.toJSONString(objects1));
    }

    private static void m58() {
        BeanSource beanSource = new BeanSource();
        beanSource.setName("source");
        beanSource.setAge(1);
//        beanSource.setData(Lists.newArrayList(new BeanSource.Data("1"), new BeanSource.Data("2")));
        System.out.println("JSON.toJSONString(beanSource) = " + JSON.toJSONString(beanSource));
        BeanTarget beanTarget = BeanConverter.convert(BeanTarget.class, beanSource);
        System.out.println("JSON.toJSONString(beanTarget) = " + JSON.toJSONString(beanTarget));
    }

    private static void m57() {
//        BeanA beanA = new BeanA();
//        beanA.setName("A");
//        BeanInnerA beanInnerA = new BeanInnerA();
//        beanInnerA.setAge(1);
//        beanA.setInner(beanInnerA);
//        System.out.println("JSON.toJSONString(beanA) = " + JSON.toJSONString(beanA));
//        BeanB beanB = BeanConverter.convert(BeanB.class, beanA);
//        System.out.println("JSON.toJSONString(beanB) = " + JSON.toJSONString(beanB));
    }

    private static void m56() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = sdf.parse("");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("parse = " + parse);
    }

    private static void m55() {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BeanHasInner beanHasInner = new BeanHasInner();
            BeanHasInner.Inner inner = new BeanHasInner.Inner();
            inner.setAge(1);
            beanHasInner.setInner(inner);
            System.out.println("111  JSON.toJSONString(beanHasInner) = " + JSON.toJSONString(beanHasInner));

        }).start();

        new Thread(() -> {

            BeanHasInner beanHasInner = new BeanHasInner();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("222  JSON.toJSONString(beanHasInner) = " + JSON.toJSONString(beanHasInner));
            BeanHasInner.Inner inner = new BeanHasInner.Inner();
            inner.setAge(2);
            beanHasInner.setInner(inner);
            System.out.println("333  JSON.toJSONString(beanHasInner.getInner()) = " + JSON.toJSONString(beanHasInner.getInner()));
        }).start();
    }

    private static void m54() {
        OptionBean optionBean = new OptionBean();
        optionBean.setSs(new ArrayList<>());
        String s = Optional.ofNullable(optionBean)
                .map(OptionBean::getSs)
                .filter(v -> v.size() == 1)
                .map(v -> v.get(0))
                .orElse(null);
        System.out.println("s = " + s);
    }

    private static void m53() {
        QuestionnaireConfigVO questionnaireConfigVO = new QuestionnaireConfigVO();
        questionnaireConfigVO.setJumpUrl("urk");
        questionnaireConfigVO.setName("name");

        ResultVO<String> stringResultVO = new ResultVO<>(true, JSON.toJSONString(questionnaireConfigVO), null, "message");
        System.out.println("JSON.toJSONString(stringResultVO) = " + JSON.toJSONString(stringResultVO));
    }

    private static void m52() {
        String s = "123";
        System.out.println(JSON.toJSONString(s));
    }

    private static void m51() {
        String s = "TSECKILL_OBTAIN_RESOURCE_ERP,TSECKILL_CHECK_PASS_SALEMAN,TSECKILL_CHECK_PASS_ERP,SECKILL_SELF_QUALIFICATION_ERP,SECKILL_RULE_NO_CHECK_MIDDLE,SECKILL_RULE_CHCEK_END_TIME_MIDDLE,SECKILL_RULE_APPLY_OPERATE,SECKILL_RULE_APPLY_CHECK_PASS_OPERATE,SECKILL_RELEASE_RESOURCE_ERP,SECKILL_PROMOTION_CHECK_REJECT_SALEMAN,SECKILL_PROMOTION_CHECK_REJECT_MIDDLE,SECKILL_PROMOTION_CHECK_PRESS_ERP,SECKILL_PROMOTION_CHECK_PASS_SALEMAN,SECKILL_PROMOTION_BUILD_FAIL_SALEMAN,SECKILL_PROMOTION_BUILD_FAIL_MIDDLE,SECKILL_OVERDUE_CHECKER,SECKILL_OBTAIN_RESOURCE_ERP,SECKILL_MAIN_POOL_UNPAY_OPERATOR,SECKILL_MAIN_POOL_ASSIGN_RESOURCE_VENDER,SECKILL_ENTER_REJECT_ERP,SECKILL_ENTER_INVITE_ERP,SECKILL_ENTER_FINISH_ERP,SECKILL_DELETE_PROMOTION_SALEMAN,SECKILL_DELETE_PROMOTION_ERP,SECKILL_CHECK_REJECT_SALEMAN,SECKILL_CHECK_REJECT_CHECKER,SECKILL_CHECK_PRESS_ERP,SECKILL_CHECK_PASS_CHECKER,SECKILL_AUCTION_SUCCESS_OPERATOR,SECKILL_AUCTION_PASS_OPERATOR,SECKILL_ADJUST_RESULT_ERP,PSECKILL_PROMOTION_CHECK_PASS_SALEMAN,PSECKILL_INVALID_M_ERP,PSECKILL_INVALID_ERP,PSECKILL_CHECK_REJECT_CERP,PSECKILL_CHECK_REJECT_AERP,PSECKILL_CHECK_PASS_CERP,PSECKILL_CHECK_PASS_AERP,PSECKILL_ACTIVITY_OFFLINE_M_ERP,PSECKILL_ACTIVITY_OFFLINE_ERP,FLASH_BUY_M_CHECK_REJECT_SCHEDULING_ERP,FLASH_BUY_M_CHECK_REJECT_ACTIVITY_ERP,FLASH_BUY_M_CHECK_PASS_ERP,FLASH_BUY_CHECK_REJECT_SCHEDULING_ERP,FLASH_BUY_CHECK_REJECT_ACTIVITY_ERP,FLASH_BUY_CHECK_PASS_ERP,FLASH_BUY_ACTIVITY_M_CHECK_PASS_ERP,FLASH_BUY_ACTIVITY_CHECK_PASS_ERP";
        String[] split = s.split(",");
        StringBuilder s2 = new StringBuilder("('");
        for (String s1 : split) {
            s2.append(s1).append("','");
        }
        System.out.println("s2 = " + s2);
    }

    private static void m50() {
        Integer i = null;
        System.out.println(String.valueOf(i));
    }

    private static void generateSceneInfoMap() {
        HashMap<String, Scene> sceneHashMap = new HashMap<>();
        sceneHashMap.put("CJ_LISTEN_TB_SP", new Scene("", Lists.newArrayList(1)));
        sceneHashMap.put("CJ_LISTEN_TB_ZZ", new Scene("", Lists.newArrayList(1)));
        sceneHashMap.put("CJ_LISTEN_TB_FP", new Scene("", Lists.newArrayList(1)));
        sceneHashMap.put("CJ_LISTEN_TB_BM", new Scene("", Lists.newArrayList(1)));
        sceneHashMap.put("CJ_LISTEN_TB_SH", new Scene("", Lists.newArrayList(1)));
        sceneHashMap.put("CJ_LISTEN_TB_SJZX", new Scene("", Lists.newArrayList(1)));
        sceneHashMap.put("CJ_LISTEN_TB_CJHD", new Scene("", Lists.newArrayList(1)));
        sceneHashMap.put("CJ_LISTEN_TB_PLBM", new Scene("", Lists.newArrayList(1, 2)));
        sceneHashMap.put("CJ_LISTEN_TB_BDTSC", new Scene("", Lists.newArrayList(1, 2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_GFYXHD", new Scene("", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_HYHD", new Scene("", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_HCHDBM", new Scene("617a440f485bb30087367a3e", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_ZCHDBM", new Scene("617a43ffdf52590077a288af", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_SCGL", new Scene("617a4448485bb30087367a45", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_XGSJ", new Scene("", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_PLBM", new Scene("", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_SPXXWH", new Scene("617a44349d3609007946c860", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_ZSDCBM", new Scene("617a43eac899ed007a685ed4", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_GFHDXQ", new Scene("", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_BZZX", new Scene("617a4488c899ed007a685edf", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_SJGL", new Scene("", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_DBSX", new Scene("", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_HDRL", new Scene("", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_SJGZT", new Scene("", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_TBSJD_XGSJCK", new Scene("617a446285f705007c39bc84", Lists.newArrayList(2)));
        sceneHashMap.put("CJ_LISTEN_YXHD_FPHDZY", new Scene("61728718dcdec00082ac54c6", Lists.newArrayList(1, 3)));
        sceneHashMap.put("CJ_LISTEN_YXHD_BMGJJSH", new Scene("61728753dcdec00082ac54ce", Lists.newArrayList(1, 3)));
        sceneHashMap.put("CJ_LISTEN_YXHD_HCHDBM", new Scene("617287e9fb2734008cbebff4", Lists.newArrayList(1, 3)));
        String s = JSON.toJSONString(sceneHashMap);
        System.out.println("s = " + s);
        //noinspection unchecked
        Map<String, Object> map = JSON.parseObject(s, Map.class);
        Map<String, Scene> map1 = new HashMap<>();
        map.forEach((scene, sceneInfo) -> {
            map1.put(scene, JSON.parseObject(JSON.toJSONString(sceneInfo), Scene.class));
        });
        System.out.println("map1 = " + map1);
    }

    private static void m47() {
        ArrayList<ScoreItemVO> scoreItemVOS = new ArrayList<>();
        scoreItemVOS.add(new ScoreItemVO("123", 2));
        scoreItemVOS.add(new ScoreItemVO("cvb", 4));
        String s = JSON.toJSONString(scoreItemVOS);
        System.out.println(s);
    }

    private static void m46() {
        BeanSource beanSource = new BeanSource();
        beanSource.setAge(10);
        BeanTarget beanTarget = new BeanTarget();
        BeanUtils.copyProperties(beanSource, beanTarget);
        System.out.println(JSON.toJSONString(beanTarget));
    }

    private static void m45() {
        String s = JSON.toJSONString(null);
        System.out.println("s = " + s);
        Man man = JSON.parseObject(s, Man.class);
        System.out.println("JSON.toJSONString(man) = " + JSON.toJSONString(man));
    }

    private static void m44() {
        BeanSource beanSource = new BeanSource();
        beanSource.setName("123");
//        BeanSource.Data data = new BeanSource.Data();
//        data.setS("345");
//        beanSource.setData(data);

        String s = JSON.toJSONString(beanSource);
        BeanTarget beanTarget = JSON.parseObject(s, BeanTarget.class);
        System.out.println(JSON.toJSONString(beanTarget));
    }

    private static void generateMap() {
        HashMap<String, String> map = new HashMap<>();
        String s = JSON.toJSONString(map);
        System.out.println(s);
    }


}
