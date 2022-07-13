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

    private static final String[] PIN_LIST = new String[]{"1000000136", "1000000142", "1000000158", "1000000174", "1000000192", "10000002", "1000000225", "1000000243", "1000000248", "1000000264",
            "1000000266", "1000000289", "10000003", "1000000302", "1000000331", "1000000365", "1000000370", "10000004", "1000000411", "1000000417", "1000000425", "1000000431", "1000000468", "1000000495", "10000005",
            "1000000513", "1000000557", "1000000567", "10000006", "1000000654", "1000000690", "1000000693", "10000007", "1000000725", "1000000742", "1000000754", "10000008", "1000000844", "1000000856", "1000000860",
            "1000000866", "1000000876", "1000000881", "10000009", "1000000900", "1000000904", "1000000921", "1000000937", "1000000952", "10000010", "1000001089", "1000001095", "10000011", "1000001132", "1000001134",
            "1000001143", "1000001165", "1000001195", "10000012", "1000001223", "1000001228", "1000001232", "1000001243", "1000001262", "1000001285", "1000001293", "10000013", "1000001302", "1000001306", "1000001343",
            "1000001351", "1000001373", "1000001393", "1000001395", "1000001396", "10000014", "1000001414", "1000001421", "1000001439", "1000001462", "1000001465", "1000001469", "1000001476", "1000001485", "10000015",
            "1000001504", "1000001509", "1000001521", "1000001522", "1000001523", "1000001590", "10000016", "1000001624", "1000001638", "1000001652", "1000001683", "1000001691", "1000001694", "10000017", "1000001700",
            "1000001701", "1000001706", "1000001737", "1000001759", "1000001762", "1000001764", "1000001770", "1000001782", "1000001798", "10000018", "1000001800", "1000001811", "1000001812", "1000001825", "1000001834",
            "1000001849", "1000001865", "10000019", "1000001901", "1000001916", "1000001927", "1000001933", "1000001934", "1000001943", "1000001967", "1000001971", "1000001972", "1000002", "10000020", "1000002002",
            "1000002012", "1000002019", "1000002020", "1000002032", "1000002061", "10000021", "1000002141", "1000002147", "10000022", "1000002241", "1000002295", "10000023", "1000002322", "1000002367", "10000024",
            "1000002422", "1000002425", "1000002447", "1000002455", "1000002467", "1000002468", "1000002469", "1000002495", "1000002499", "10000025", "1000002505", "1000002523", "1000002534", "1000002571", "1000002576",
            "1000002596", "1000002599", "10000026", "1000002621", "1000002628", "1000002662", "1000002666", "1000002668", "1000002696", "10000027", "1000002701", "1000002717", "1000002725", "1000002743", "1000002744",
            "1000002746", "1000002752", "1000002763", "1000002791", "1000002796", "10000028", "1000002800", "1000002815", "1000002816", "1000002819", "1000002826", "1000002833", "1000002836", "1000002846", "10000029",
            "1000003", "1000003039", "1000003042", "1000003051", "10000031", "1000003112", "1000003129", "1000003147", "1000003157", "1000003158", "1000003168", "1000003180", "1000003183", "10000032", "1000003201",
            "1000003244", "1000003263", "10000033", "1000003332", "1000003346", "1000003348", "1000003363", "1000003367", "10000034", "1000003448", "1000003466", "10000035", "1000003533", "1000003568", "1000003571",
            "1000003594", "10000036", "1000003663", "1000003664", "1000003685", "1000003691", "10000037", "1000003788", "10000038", "1000003819", "1000003835", "10000039", "1000003925", "1000003986", "10000040",
            "1000004064", "1000004065", "10000041", "1000004123", "10000042", "1000004259", "1000004292", "10000043", "1000004328", "1000004350", "1000004368", "10000044", "1000004445", "10000045", "10000046",
            "1000004661", "1000004663", "10000047", "1000004784", "1000005112", "1000005115", "1000005222", "1000005447", "1000005512", "1000005657", "1000005670", "1000005736", "1000005823", "10000062", "10000063",
            "1000006382", "10000064", "1000006462", "10000065", "10000066", "1000006644", "1000006649", "1000006670", "10000067", "1000006721", "1000006782", "10000068", "1000006804", "10000069", "1000006981", "10000070",
            "10000071", "10000072", "1000007205", "1000007245", "10000073", "1000007363", "10000074", "1000007466", "1000007507", "1000007527", "1000007541", "1000007554", "10000076", "10000077", "10000078", "1000008122",
            "10000082", "10000083", "10000084", "10000085", "10000086", "1000008614", "1000008648", "10000087", "1000008721", "10000088", "1000008814", "1000008851", "10000089", "10000090", "10000091", "1000009116",
            "10000092", "10000093", "10000094", "10000095", "1000009541", "10000096", "10000097", "10000098", "1000009821", "10000099", "100001", "10000100", "10000101", "10000102", "1000010241", "1000010246",
            "10000103", "1000010322", "10000104", "1000010405", "10000105", "10000106", "10000107", "10000108", "1000010825", "10000109", "1000010912", "1000010923", "10000110", "10000111", "10000112", "1000011210",
            "1000011225", "10000113", "10000114", "1000011442", "1000011484", "10000115", "10000116", "1000011663", "10000117", "1000011761", "10000118", "10000119", "10000120", "10000121", "10000122", "1000012223",
            "1000012243", "1000012286", "10000123", "10000124", "10000125", "1000012544", "10000126", "10000127", "1000012781", "10000128", "10000129", "10000130", "1000013083", "10000131", "1000013173", "10000132",
            "1000013205", "10000133", "10000134", "1000013402", "10000135", "10000136", "10000137", "10000138", "1000013882", "10000139", "1000013914", "1000013962", "1000013972", "1000013981", "10000140", "10000141",
            "1000014142", "10000142", "1000014286", "10000143", "1000014349", "10000144"};

    private static final String[] CODE_HD_LIST = new String[]{"material_maintain", "meeting_activity", "channel_resource", "inventory_activity", "goods_marking", "item_promotion", "shop_promotion",
            "promotion_inventory", "united_coupon", "discount_promotion", "expanded_gold", "limit_amount", "platform_presale", "shop_reduction", "platform_promotion", "outside_promotion", "gift_promotion "};

    private static final String[] CODE_YX_LIST = new String[]{"hd_activity", "daily_activity", "jd_seckill", "brand_sale", "plus_vip", "channel_activity "};

    private static final int[] QUERY_TYPE_LIST = {1, 2, 3, 4};

    private static final String CHAR_LIST = "1234567890";

    private static final String[] ORDER_BY_LIST = new String[]{"LAST_ASSIGN_TIME_ASC", "LAST_ASSIGN_TIME_DESC", "CARD_PUBLISH_TIME_ASC", "CARD_PUBLISH_TIME_DESC", "CARD_APPLY_END_TIME_ASC", "CARD_APPLY_END_TIME_DESC"};

    private static final Random commonRandom = new Random();

    public static void main(String[] args) throws Exception {
        generateTestData();
    }

    private static void generateTestData() throws IOException {
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
            if (probabilityGeneration(16)) {
                List<String> marketSceneTags = new ArrayList<>();
                int i2 = random.nextInt(3) + 1;
                for (int j = 0; j < i2; j++) {
                    marketSceneTags.add(CODE_YX_LIST[random.nextInt(CODE_YX_LIST.length)]);
                }
                squareActivityQuery.setMarketSceneTags(marketSceneTags);
            }
            // activityTypeTags
            if (probabilityGeneration(16)) {
                List<String> activityTypeTags = new ArrayList<>();
                int i3 = random.nextInt(3) + 1;
                for (int j = 0; j < i3; j++) {
                    activityTypeTags.add(CODE_HD_LIST[random.nextInt(CODE_HD_LIST.length)]);
                }
                squareActivityQuery.setActivityTypeTags(activityTypeTags);
            }
            // cardActivityName
            if (probabilityGeneration(12)) {
                squareActivityQuery.setCardActivityName(String.valueOf(CHAR_LIST.charAt(random.nextInt(CHAR_LIST.length()))));
            }
            // activityBeginTime
            if (probabilityGeneration(15)) {
                Calendar instance = Calendar.getInstance();
                instance.add(Calendar.DATE, random.nextInt(90));
                Date afterDate = instance.getTime();
                instance.add(Calendar.DATE, -random.nextInt(180));
                Date beforeDate = instance.getTime();
                squareActivityQuery.setMinActivityBeginTime(beforeDate);
                squareActivityQuery.setMaxActivityBeginTime(afterDate);
            }
            // orderBy
            squareActivityQuery.setOrderBy(ORDER_BY_LIST[random.nextInt(ORDER_BY_LIST.length)]);
            // 文件写入
            String jsonString = JSON.toJSONString(squareActivityQuery);
            if (i != 0) {
                // 写换行
                bufferedWriter.write("\n");
            }
            bufferedWriter.write(jsonString);
            bufferedWriter.write("|");
            // page
            if (probabilityGeneration(4)) {
                bufferedWriter.write((random.nextInt(9) + 2) + "");
            } else {
                bufferedWriter.write("1");
            }
            bufferedWriter.write("|");
            // pageSize
            if (probabilityGeneration(10)) {
                bufferedWriter.write("15");
            } else {
                bufferedWriter.write("10");
            }
        }
        bufferedWriter.close();
    }

    /**
     * 返回true的概率: 1/rate
     */
    private static boolean probabilityGeneration(int rate) {
        int i = commonRandom.nextInt(9999999);
        return i % rate == 0;
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
