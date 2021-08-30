package lq.test;

import com.alibaba.fastjson.JSON;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"ConstantConditions", "UnnecessaryLocalVariable"})
public class ListTest {
    public static void main(String[] args) {
//        m10();
        long i = 1;
        Long a = 3L;
        System.out.println(a != i);
    }

    private static void m10() {
        Man man = new LittleMan();
        man.setName("123");
        System.out.println(man.showName());
    }

    private static void m9() {
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

    private static void m8() {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integers.add(i);
        }

        integers = integers.stream()
                .filter(v -> v % 3 == 0)
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(integers));
    }


    private static void m7() {
        int i = 0;
        Assert.isTrue(i == 1, "asd");
    }

    private static void m6() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "12312434!@#!@#");
        map.put(1, "12312434!@#!@#");
        System.out.println(JSON.toJSONString(map));

        String mapStr = "{0:'12312434!@#!@#',1:'12312434!@#!@#'}";
        HashMap map1 = JSON.parseObject(mapStr, HashMap.class);
        System.out.println(JSON.toJSONString(map1));
    }

    private static void m5() {
        String s1 = "asdasdasdasd%s, , ,%s ads %s";
        Long a1 = 102746321L;
        String s2 = "1";
        String s3 = "2";
        String format = String.format(s1, a1, s2, s3);
        System.out.println("format = " + format);
    }

    private static void m4() {
        ArrayList<VenderColType> venderColTypes = new ArrayList<>();
        venderColTypes.add(new VenderColType(1L, 1));
        venderColTypes.add(new VenderColType(2L, 12));
        venderColTypes.add(new VenderColType(3L, 13));
        venderColTypes.add(new VenderColType(4L, 14));
        venderColTypes.add(new VenderColType(5L, 16));
        System.out.println(JSON.toJSONString(venderColTypes));
    }

    private static void m3() {
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

    private static void method() {
        Man man = new Man();
        man.setAge(1);
        man.setName("lq");
        System.out.println(JSON.toJSONString(man));
    }

    private static void testTrim() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("  123123");
        for (String string : strings) {
            string = string.trim();

            System.out.println(string);
            System.out.println(Long.valueOf(string));
        }
    }

    private static void testInstanceof() {
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

    private static void m2() {
        WhiteBackgroundImgVO whiteBackgroundImgVO = new WhiteBackgroundImgVO();
        whiteBackgroundImgVO.setWhiteBackgroundImg("WhiteBackgroundImg");
        whiteBackgroundImgVO.setBannerId("BannerId");
        System.out.println(JSON.toJSON(whiteBackgroundImgVO));
    }

    private static void m1() {
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
