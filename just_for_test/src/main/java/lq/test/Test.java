package lq.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.rocketmq.common.ThreadFactoryImpl;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.util.StopWatch;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SuppressWarnings({"ConstantConditions", "UnnecessaryLocalVariable", "AlibabaAvoidManuallyCreateThread", "unused"})
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

    public static void main(String[] args) throws Exception {
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
        BeanSource.Data data = new BeanSource.Data();
        data.setS("345");
        beanSource.setData(data);

        String s = JSON.toJSONString(beanSource);
        BeanTarget beanTarget = JSON.parseObject(s, BeanTarget.class);
        System.out.println(JSON.toJSONString(beanTarget));
    }

    private static void generateMap() {
        HashMap<String, String> map = new HashMap<>();
        String s = JSON.toJSONString(map);
        System.out.println(s);
    }

    private static void m43() {
        String s = "{}";
        Map<String, String> map = JSON.parseObject(s, Map.class);
        System.out.println(JSON.toJSONString(map));
        String o = map.get("1");
        System.out.println(o);
    }

    private static void m42() {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
//设置加密密钥
        textEncryptor.setPassword("qianqian");
//加密信息
        String encryptedText = textEncryptor.encrypt("123");
        System.out.println("encryptedText:" + encryptedText);
    }

    private static void m41() {
        Integer i = 200;
        int a = 200;
        System.out.println(i.equals(a));
    }

    private static void m40() {
        Bean bean = new Bean();
        System.out.println("JSON.toJSONString(bea) = " + JSON.toJSONString(bean));
    }

    private static void m39() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        System.out.println("timestamp = " + timestamp);
        String sign = DigestUtils.md5DigestAsHex(("actcenter" + "3E5DA600DF03665A85E66E276EECAD7B" + timestamp).getBytes()).toUpperCase();
        System.out.println("sign = " + sign);
    }

    private static void m38() {
        SyncAreaManagersAuth event = new SyncAreaManagersAuth();
        event.setActivityId(123L);
        event.setAreaId(234L);
        event.setAddSet(Sets.newHashSet("pin1", "pin2"));
        event.setDelSet(Sets.newHashSet("pin3", "pin4"));

        String eventId = "UUID_" + UUID.randomUUID().toString().replaceAll("-", "");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sendTime", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS").format(System.currentTimeMillis()));
        jsonObject.put("eventType", "EVENT_TYPE");
        jsonObject.put("data", JSONObject.toJSONStringWithDateFormat(event, "yyyy-MM-dd HH:mm:ss"));
        jsonObject.put("eventId", eventId);
        System.out.println(jsonObject.toJSONString());
    }

    private static void m37() {
        long l = System.currentTimeMillis();
        for (long i = 0; i < 100000000000L; i++) {
            int a = 16;
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);
        long l2 = System.currentTimeMillis();
        for (long c = 0; c < 100000000000L; c++) {
            int b = 1 << 4;
        }
        long l3 = System.currentTimeMillis();
        System.out.println(l3 - l2);
    }

    private static void m36() throws InterruptedException {
        StopWatch clock = new StopWatch();
        for (int i = 0; i < 4; i++) {
            clock.start();
            Thread.sleep(1000);
            clock.stop();
            System.out.println(clock.getLastTaskTimeMillis());
        }
        System.out.println(clock.getTotalTimeMillis());
    }

    private static void m35() throws Exception {
        LinkedList<Integer> integers = new LinkedList<>();
        LinkedList<String> l2 = new LinkedList<>();
        for (int i = 0; i < 99; i++) {
            integers.add(i);
        }

        integers.parallelStream()
                .forEach(v -> {
                    Random random = new Random();
                    l2.add("1");
                });
        System.out.println(JSON.toJSONString(l2));
    }

    private static void m34() throws InterruptedException {
        StopWatch clock = new StopWatch();
        clock.start("TaskOneName");
        Thread.sleep(300);// 任务一模拟休眠3秒钟
        clock.stop();
        clock.start("TaskTwoName");
        Thread.sleep(300);// 任务一模拟休眠10秒钟
        clock.stop();
        clock.start("TaskThreeName");
        Thread.sleep(400);// 任务一模拟休眠10秒钟
        clock.stop();
        System.out.println(clock.getTotalTimeMillis());
    }

    private static void m33() {
        String regex = ",|，";
//        String regex = "[,，]";
        String s = "1231,23，，4123";
        String[] split = s.split(regex);
        System.out.println(Arrays.toString(split));
    }

    private static void m32() {
        // 创建2000个随机数组成的数组:
        long[] array = new long[2000];
        long expectedSum = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = random();
            expectedSum += array[i];
        }
        System.out.println("Expected sum: " + expectedSum);
        // fork/join:
        ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        Long result = ForkJoinPool.commonPool().invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }

    static long random() {
        return random.nextInt(10000);
    }


    private static void m31() {
        Man man = new Man();
        man.setAge(1);
        man.setName("123");
        String s = JSON.toJSONString(man);
        System.out.println("s = " + s);
        Man man1 = JSON.parseObject(s, Man.class);
        System.out.println("man1 = " + man1);
        System.out.println("JSON.toJSONString(man1) = " + JSON.toJSONString(man1));
    }

    private static void m30() {
        int arg = 1;
        switch (arg) {
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
                break;
            case 3:
                System.out.println(3);
                break;
            default:
                System.out.println("-1");
                break;
        }
    }

    private static void m29() {
        String s = "10033233497458";
        System.out.println(NumberUtils.isNumber(s));
    }

    private static void m28() {
        BigDecimal bigDecimal = new BigDecimal(2123123);
        String s = bigDecimal.toString();
        System.out.println(s);
    }

    private static void m27() {
        BeanSource beanSource = new BeanSource();
        beanSource.setName("123");
        ArrayList<String> names = Lists.newArrayList("1", "2", "3");
        BeanTarget beanTarget = new BeanTarget();
        BeanUtils.copyProperties(beanSource, beanTarget);
        System.out.println(JSON.toJSONString(beanTarget));
    }

    private static void m26() {
        String s = testTryReturn();
        System.out.println("s = " + s);
    }

    private static String testTryReturn() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            return e.getMessage();
        }

        return "12";
    }

    private static void m25() {
        int a = 1;
        System.out.println(a % 2000 == 0 ? a / 2000 : (a / 2000 + 1));
    }

    private static void m24() {
        Integer integer = 1;
        TestEnum testEnum = TestEnum.parseOf(integer);
        System.out.println("testEnum = " + testEnum);
        switch (testEnum) {
            case ENUM0:
                System.out.println(0);
                break;
            case ENUM1:
                System.out.println(1);
                break;
            case ENUM2:
                System.out.println(2);
                break;
            default:
                System.out.println("default");
                break;
        }
    }

    private static void testCache() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .build();
        System.out.println(cache);
    }

    private static void testMd5() throws NoSuchAlgorithmException {
        // 生成一个MD5加密计算摘要
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md.update("e10adc3949ba59abbe56e057f20f883e".getBytes());
        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        String s = new BigInteger(1, md.digest()).toString(16);
        System.out.println("s = " + s);
    }

    private static void m22() throws Exception {
        TestException testException = new TestException("1", "123");
        Exception exception = new Exception();
        throw exception;
    }

    private static void m0922() throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //指定初始容量1来创建一个HashMap
        HashMap<Integer, Integer> map = new HashMap(1);
        //获取HashMap整个类
        Class<?> mapType = map.getClass();
        //获取指定属性，也可以调用getDeclaredFields()方法获取属性数组
        Field threshold = mapType.getDeclaredField("threshold");
        //将目标属性设置为可以访问
        threshold.setAccessible(true);
        //获取指定方法，因为HashMap没有容量这个属性，但是capacity方法会返回容量值
        Method capacity = mapType.getDeclaredMethod("capacity");
        //设置目标方法为可访问
        capacity.setAccessible(true);
        //打印刚初始化的HashMap的容量、阈值和元素数量
        System.out.println("容量：" + capacity.invoke(map) + "    阈值：" + threshold.get(map) + "    元素数量：" + map.size());
        for (int i = 0; i < 17; i++) {
            map.put(i, i);
            //动态监测HashMap的容量、阈值和元素数量
            System.out.println("容量：" + capacity.invoke(map) + "    阈值：" + threshold.get(map) + "    元素数量：" + map.size());
        }
    }

    private static void m920() {
        String s = "你好!";
        System.out.println("s.length() = " + s.length());
        String s1 = "adf!";
        System.out.println("s1.length() = " + s1.length());
    }

    private static void m1517() {
        System.out.println(1);
        POOL_EXECUTOR.execute(Test::testVoid);
        System.out.println(2);
        POOL_EXECUTOR.shutdownNow();
    }

    private static void threadSleepUsePool(int seconds) {
        POOL_EXECUTOR.execute(() -> {
            for (int i = 0; i < seconds; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("threadSleepUsePool: " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void threadSleep(int seconds) {
        new Thread(() -> {
            for (int i = 0; i < seconds; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("threadSleep: " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void testVoid() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void m21() {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(JSON.toJSONString(map));
        map.put("key", map);
        System.out.println(map);
        System.out.println(JSON.toJSONString(map));
    }

    private static void m20() {
        Boolean s = false;
        String format = String.format("asdfsdfsadf%s", s);
        System.out.println(format);
    }

    private static void m19() {
        // 每秒产生一个令牌
        POOL_EXECUTOR.execute(() -> {
            while (true) {
                bucketSize++;
                try {
                    // 每隔一秒增加一个令牌数量
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("令牌桶大小:%s\n", bucketSize);
            }
        });

        // 每4s产生一个随机大小的数据包
        POOL_EXECUTOR.execute(() -> {
            Random random = new Random();
            while (true) {
                try {
                    // 4s产生一个随机大小的数据包
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                integers.add(random.nextInt(16));
                System.out.println("数据包, 队列内容: " + integers);
            }
        });

        // 10ms检查一次数据包队列是不是可以发送数据了
        POOL_EXECUTOR.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (integers.size() > 0) {
                    Integer integer = integers.getFirst();
                    if (bucketSize >= integer) {
                        bucketSize -= integer;
                        integers.removeFirst();
                        System.out.println("发送数据包, 数据包大小" + integer);
                    }
                }
            }
        });
    }

    private static void m18() {
        Optional<Man> man = Optional.ofNullable(new Man());
        System.out.println(man);
        System.out.println(man.get());
        System.out.println("---");
        Man man1 = (Man) Optional.ofNullable(null).orElse(new Man());
        System.out.println(man1);
    }

    private static void m17() {
        ArrayList<Man> list = Lists.newArrayList(
                new Man(22, "11"),
                new Man(23, "12"),
                new Man(24, "13"),
                new Man(21, "14"),
                new Man(22, "15"),
                new Man(21, "16"),
                new Man(25, "17"),
                new Man(26, "18"),
                new Man(24, "19"),
                new Man(23, "10"),
                new Man(23, "21"),
                new Man(29, "22")
        );
        Map<Integer, Long> collect = list.stream()
                .collect(Collectors.groupingBy(Man::getAge, Collectors.counting()));
        System.out.println(JSON.toJSONString(collect));
    }

    private static void m16() {
        People people = new People();
        people.setAge(1);
        people.setName("123");
        Man man = new Man();
        man.setAge(2);
        BeanUtils.copyProperties(people, man);
        System.out.println(JSON.toJSONString(man));
    }

    private static void m15() {
        LinkedList<String> strings = new LinkedList<>();
        strings.add(null);
        strings.add(null);
        strings.add(null);
        strings.add(null);
        System.out.println(JSON.toJSONString(strings));
    }

    private static void m14() {
        final CountDownLatch latch = new CountDownLatch(20);
        for (int i = 0; i < 100; i++) {
            POOL_EXECUTOR.execute(() -> {
                latch.countDown();
                System.out.println(JSON.toJSONString(latch));
            });
        }
    }

    private static void m12() {
        String s = "   00234234 ";
        s = s.trim();
        System.out.println(NumberUtils.isDigits(s));
        System.out.println(Long.valueOf(s));
    }

    private static void m13() {
        final Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            Runnable runnable = () -> {
                Man man = getMan();
                man.setAge(finalI);
                System.out.println("当前线程:" + Thread.currentThread().getName() + ", 第一次获取:" + JSON.toJSONString(man));
                int a = random.nextInt();
                if (a % 9 == 0) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            POOL_EXECUTOR.execute(runnable);
        }
    }

    private void manRemove() {
        THREAD_LOCAL.remove();
    }

    private static Man getMan() {
        Man man = THREAD_LOCAL.get();
        if (man == null) {
            man = new Man();
            String name = Thread.currentThread().getName();
            man.setName(name);
            THREAD_LOCAL.set(man);
        }
        return man;
    }

    private void m11() {
        long i = 1;
        Long a = 3L;
        System.out.println(a != i);
    }

    private void m10() {
        Man man = new LittleMan();
        man.setName("123");
        System.out.println(man.showName());
    }

    private void m9() {
        PageVO<Man> manPageVO = new PageVO<>();
        List<Man> mens = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mens.add(new Man(i, "asd" + i));
        }
        manPageVO.setItems(mens);
        String s = JSON.toJSONString(manPageVO);
        System.out.println(s);
        System.out.println(JSON.toJSONString(JSON.parseObject(s, PageVO.class)));
    }

    private void m8() {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integers.add(i);
        }

        integers = integers.stream()
                .filter(v -> v % 3 == 0)
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(integers));
    }


    private void m7() {
        int i = 0;
        Assert.isTrue(i == 1, "asd");
    }

    private void m6() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "12312434!@#!@#");
        map.put(1, "12312434!@#!@#");
        System.out.println(JSON.toJSONString(map));

        String mapStr = "{0:'12312434!@#!@#',1:'12312434!@#!@#'}";
        HashMap map1 = JSON.parseObject(mapStr, HashMap.class);
        System.out.println(JSON.toJSONString(map1));
    }

    private void m5() {
        String s1 = "asdasdasdasd%s, , ,%s ads %s";
        Long a1 = 102746321L;
        String s2 = "1";
        String s3 = "2";
        String format = String.format(s1, a1, s2, s3);
        System.out.println("format = " + format);
    }

    private void m4() {
        ArrayList<VenderColType> venderColTypes = new ArrayList<>();
        venderColTypes.add(new VenderColType(1L, 1));
        venderColTypes.add(new VenderColType(2L, 12));
        venderColTypes.add(new VenderColType(3L, 13));
        venderColTypes.add(new VenderColType(4L, 14));
        venderColTypes.add(new VenderColType(5L, 16));
        System.out.println(JSON.toJSONString(venderColTypes));
    }

    private void m3() {
        for (int j = 3; j > -6; j--) {
            try {
                System.out.println(j);
                if (j == 0) {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                System.out.println(e);
//                throw new RuntimeException();
            }
        }
    }

    private void method() {
        Man man = new Man();
        man.setAge(1);
        man.setName("lq");
        System.out.println(JSON.toJSONString(man));
    }

    private void testTrim() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("  123123");
        for (String string : strings) {
            string = string.trim();

            System.out.println(string);
            System.out.println(Long.valueOf(string));
        }
    }

    private void testInstanceof() {
        Integer[] integers = {1, 2, 3};
        Object integers1 = integers;
        if (integers1 instanceof Integer[]) {
            System.out.println("Integer[]");
        }
        if (integers1 instanceof int[]) {
            System.out.println("int[]");
        }
        System.out.println("--------------");
        int[] int1 = {1, 2, 3};
        Object int2 = int1;
        if (int2 instanceof Integer[]) {
            System.out.println("Integer[]");
        }
        if (int2 instanceof int[]) {
            System.out.println("int[]");
        }
    }

    private void m2() {
        WhiteBackgroundImgVO whiteBackgroundImgVO = new WhiteBackgroundImgVO();
        whiteBackgroundImgVO.setWhiteBackgroundImg("WhiteBackgroundImg");
        whiteBackgroundImgVO.setBannerId("BannerId");
        System.out.println(JSON.toJSON(whiteBackgroundImgVO));
    }

    private void m1() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(null);
        strings.add(null);
        strings.add(null);
        strings.add(null);
        strings.add(null);
        System.out.println("strings.size() = " + strings.size());
        for (String string : strings) {
            System.out.println(string.length());
        }
    }
}
