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

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "unused"})
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

    private static int a;

    private static final int THREADS_COUNT = 20;
    //    public static int race = 0;
    public static volatile int race = 0;

    public static synchronized void increase() {
        race++;
    }

    private static final String[] PIN_LIST = new String[]{"10000139", "10000348", "10000782", "10000978", "10001076", "10001837"
            , "10002114", "10002115", "10002345", "10003020", "10003695", "10003908", "10004147", "10004250", "10004684", "10004884"
            , "10005422", "10005567", "10008345", "10008423", "10008973", "10009865", "10010313", "10011915", "10011935", "10011959"
            , "10011961", "10012267", "10014123", "10014128", "10014142", "10014445", "10014533", "10015033", "10015190", "10016483"
            , "10016516", "10016520", "10016829", "10017074", "10017454", "10017456", "10018676", "10018856", "10018864", "10019421"
            , "10019483", "10019724", "10021401", "10022446", "10023208", "10024014", "10024865", "10025161", "10025186", "10025842"
            , "10026833", "10028641", "10028723", "10028805", "10028826", "10029191", "10029602", "10029878", "10029965", "10030155"
            , "10030625", "10030825", "10031224", "10031292", "10031364", "10031421", "10031431", "10031454", "10031587", "10032316"
            , "10032365", "10032533", "10032988", "10033108", "10034128", "10034540", "10034580", "10034609", "10035348", "10036876"
            , "10037056", "10037938", "10037939", "10038042", "10038063", "10039046", "10039762", "10040287", "10040796", "10040829"
            , "10040858", "10040908", "10041096", "10091706", "10091777", "10092994", "10093043", "10093861", "10094287", "10095038"
            , "10096069", "10096269", "10097031", "10097061", "10097452", "10097615", "10097813", "10098514", "10098731", "10098758"
            , "10099605", "10100257", "10100846", "10100870", "10100973", "10101012", "10101094", "10101098", "10102895", "10103156"
            , "10103222", "10103845", "10104280", "10104965", "10105515", "10108581", "10110053", "10111535", "10112068", "10112533"
            , "10112767", "10112804", "10113230", "10113431", "10114262", "10114470", "10114884", "10115349", "10115367", "10116026"
            , "10116221", "10116372", "10117433", "10117831", "10117896", "10117917", "10117982", "10118060", "10118928", "10118929"
            , "10119694", "10119887", "10119889", "10120415", "10120433", "10120448", "10120451", "10121001", "10121728", "10122656"
            , "10123335", "10123398", "10123502", "10123505", "10123677", "10123723", "10124194", "10124706", "10125314", "10125703"
            , "10126548", "10127752", "10127811", "10128063", "10128124", "10128173", "10128271", "10128362", "10128377", "10128918"
            , "10129005", "10129045", "10129198", "10129477", "10129512", "10129527", "10129622", "10129631", "10130955", "10130998"
            , "10131070", "10131633", "10169689", "10169832", "10171092", "10172273", "10172410", "10173381", "10173397", "10173398"
            , "10173399", "10173400", "10173406", "10173563", "10173642", "10173664", "10173806", "10174116", "10174338", "10174426"
            , "10174583", "10175080", "10175141", "10175274", "10175333", "10175407", "10175462", "10175724", "10177850", "10179270"
            , "10179638", "10179692", "10179787", "10180234", "10180451", "10181466", "10181483", "10181499", "10181502", "10181521"
            , "10181522", "10181550", "10181731", "10182065", "10182164", "10182491", "10183222", "10183974", "10184131", "10184501"
            , "10185112", "10185296", "10185402", "10185528", "10185869", "10185944", "10185947", "10186069", "10186485", "10186499"
            , "10187277", "10187582", "10187781", "10187986", "10188139", "10188146", "10188172", "10188427", "10188485", "10188583"
            , "10188611", "10188946", "10189357", "10189391", "10189427", "10189464", "10190042", "10190524", "10190817", "10191109"
            , "10191652", "10192286", "10192401", "10192456", "10192728", "10193186", "10193948", "10196294", "10196311", "10196320"
            , "10196437", "10196538", "10196552", "10196556", "10196596", "10196648", "10197757", "10198458", "10199422", "10199479"
            , "10199578", "10201116", "10201215", "10201226", "10201244", "10201462", "10202573", "10202739", "10203895", "10203915"
            , "10203964", "10204060", "10204479", "10204948", "10205197", "10205467", "10205475", "10205530", "10205585", "10206017"
            , "10206206", "10206920", "10207122", "10207462", "10207469", "10207754", "10207772", "10207915", "10208877", "10209541"
            , "10209978", "10210036", "10210604", "10211165", "10211432", "10212280", "10213337", "10213522", "10213636", "10213844"
            , "10213903", "10213949", "10214391", "10215235", "10215811", "10215879", "10216346", "10217037", "10217621", "10217640"
            , "10217692", "10217694", "10217838", "10217918", "10218408", "10218416", "10218510", "10218529", "10218551", "10219577"
            , "10219762", "10219839", "10220216", "10220488", "10220639", "10220670", "10220734", "10220967", "10221059", "10221174"
            , "10221553", "102217", "10222034", "10222154", "10222405", "10222801", "10222885", "10223145", "10223322", "10223348"
            , "10224295", "10224307", "10224473", "10224474", "10224917", "10225033", "10225048", "10225067", "10225143", "10225154"
            , "10225294", "10225595", "10227062", "10227379", "10227664", "10227695", "10227709", "10227859", "10227967", "10228008"
    };

    private static final String[] CODE_HD_LIST = new String[]{"material_maintain", "meeting_activity", "channel_resource", "inventory_activity", "goods_marking", "item_promotion", "shop_promotion", "promotion_inventory", "united_coupon",
            "discount_promotion", "expanded_gold", "limit_amount", "platform_presale", "shop_reduction", "platform_promotion", "outside_promotion"};

    private static final String[] CODE_YX_LIST = new String[]{"hd_activity", "channel_activity", "daily_activity", "jd_seckill", "brand_sale", "plus_vip", "home_resource", "coupon_center"};

    private static final int[] QUERY_TYPE_LIST = {1, 2, 3, 4};

    private static final String CHAR_LIST = "1234567890";

    private static final String[] ORDER_BY_LIST = new String[]{"LAST_ASSIGN_TIME_ASC", "LAST_ASSIGN_TIME_DESC", "CARD_PUBLISH_TIME_ASC", "CARD_PUBLISH_TIME_DESC", "CARD_APPLY_END_TIME_ASC", "CARD_APPLY_END_TIME_DESC"};

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        String filepath = "/Users/liqian477/test_data";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath));
        // 生成1万条数据
        for (int i = 0; i < 10000; i++) {
            // 生成一条数据
            SquareActivityQuery squareActivityQuery = new SquareActivityQuery();
            squareActivityQuery.setCardPinType(20);
            // pin
            squareActivityQuery.setPin(PIN_LIST[random.nextInt(PIN_LIST.length)]);
            // queryType
            squareActivityQuery.setQueryType(QUERY_TYPE_LIST[random.nextInt(QUERY_TYPE_LIST.length)]);
            // marketSceneTags
            int i1 = random.nextInt(999999);
            if (i1 % 8 == 0) {
                List<String> marketSceneTags = new ArrayList<>();
                int i2 = random.nextInt(3) + 1;
                for (int j = 0; j < i2; j++) {
                    marketSceneTags.add(CODE_YX_LIST[random.nextInt(CODE_YX_LIST.length)]);
                }
                squareActivityQuery.setMarketSceneTags(marketSceneTags);
            }
            // activityTypeTags
            int i2 = random.nextInt(999999);
            if (i2 % 8 == 0) {
                List<String> activityTypeTags = new ArrayList<>();
                int i3 = random.nextInt(3) + 1;
                for (int j = 0; j < i3; j++) {
                    activityTypeTags.add(CODE_HD_LIST[random.nextInt(CODE_HD_LIST.length)]);
                }
                squareActivityQuery.setActivityTypeTags(activityTypeTags);
            }
            // cardActivityName
            int i3 = random.nextInt(999999);
            if (i3 % 12 == 0) {
                squareActivityQuery.setCardActivityName(String.valueOf(CHAR_LIST.charAt(random.nextInt(CHAR_LIST.length()))));
            }
            // activityBeginTime
            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.DATE, random.nextInt(90));
            Date afterDate = instance.getTime();
            instance.add(Calendar.DATE, -random.nextInt(180));
            Date beforeDate = instance.getTime();
            squareActivityQuery.setMinActivityBeginTime(beforeDate);
            squareActivityQuery.setMaxActivityBeginTime(afterDate);
            // orderBy
            squareActivityQuery.setOrderBy(ORDER_BY_LIST[random.nextInt(ORDER_BY_LIST.length)]);
            // 文件写入
            String jsonString = JSON.toJSONString(squareActivityQuery);
            if (i != 0) {
                bufferedWriter.write("\n");
            }
            bufferedWriter.write(jsonString);
            bufferedWriter.write("|");
            bufferedWriter.write((random.nextInt(10) + 1) + "");
        }
        bufferedWriter.close();
    }

    private static void m90() throws InterruptedException {
        Future<Integer> submit = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i = " + i);
            }
            return 1;
        });
        Integer integer = 2;
        try {
            integer = submit.get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("异常1");
        }
        System.out.println("integer = " + integer);
        TimeUnit.SECONDS.sleep(20);
    }

    private static void m65() {
//        String s = "1230123.1";
        String s = "000123012300012301230001230123000123012300012301230001230123000123012300012301230001230123000123012300012301230001230123000123012300012301230001230123000123012300012301230001230123";
//        String s = "-0012301231";
        System.out.println(NumberUtils.isParsable(s));
        System.out.println(NumberUtils.isDigits(s));
    }

    private static void m89() {
        Integer a = null;
        if (0 < a) {
            System.out.println(1);
        }
    }

    private static void m88() {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println("1");
        integers.parallelStream()
                .forEach(v -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println("v = " + v);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        System.out.println("2");
    }

    private static void m87() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        ca.add(Calendar.DAY_OF_MONTH, 1);
        Date time = ca.getTime();
        long l = System.currentTimeMillis();
        long l1 = time.getTime() - l;
        System.out.println("l1 = " + l1);
        Date date = new Date();
    }

    private static void m86() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("./emails.txt", true);
            fos.write("123123123".getBytes());
            fos.write("456456".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void m85() throws InterruptedException {
        // 创建一个信号量
        Semaphore semaphore = new Semaphore(3);
        new Thread(() -> {
            for (int i = 1; i < 100; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i = " + i);
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(10);
        new Thread(() -> {
            for (int j = 0; j < 100; j++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("j = " + j);
                semaphore.release();
            }
        }).start();
    }

    private static void m84() {
        Integer a = 1;
        Integer a1 = Integer.valueOf(1);
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Integer l = 128;
        Integer h = 128;
        Long g = 3L;
        System.out.println(l == h);//f
        System.out.println(a1 == a);//t
        System.out.println(c == d);//t
        System.out.println(e == f);//f
        System.out.println(c == (a + b));//t
        System.out.println(c.equals(a + b));//t
        System.out.println(g == (a + b));//t
        System.out.println(g.equals(a + b));//f
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


}
