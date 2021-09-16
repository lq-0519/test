package lq.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.rocketmq.common.ThreadFactoryImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SuppressWarnings({"ConstantConditions", "UnnecessaryLocalVariable", "AlibabaAvoidManuallyCreateThread"})
public class ListTest {

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
    private static LinkedList<Integer> integers = new LinkedList<>();

    public static void main(String[] args) {
        String s = "你好!";
        System.out.println("s.length() = " + s.length());
        String s1 = "adf!";
        System.out.println("s1.length() = " + s1.length());
    }

    private static void m1517() {
        System.out.println(1);
        POOL_EXECUTOR.execute(ListTest::testVoid);
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
