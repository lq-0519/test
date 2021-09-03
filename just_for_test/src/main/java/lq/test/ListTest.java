package lq.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.rocketmq.common.ThreadFactoryImpl;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SuppressWarnings({"ConstantConditions", "UnnecessaryLocalVariable"})
public class ListTest {

    /**
     * 创建测试用线程池
     */
    private static final ExecutorService TEST_THREAD_POOL = new ThreadPoolExecutor(
            5,
            10,
            0, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(20),
            new ThreadFactoryImpl("test"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private static final ThreadLocal<Man> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(20);
        for (int i = 0; i < 100; i++) {
            TEST_THREAD_POOL.execute(() -> {
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
            TEST_THREAD_POOL.execute(runnable);
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
