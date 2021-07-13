package lq.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

@SuppressWarnings({"ConstantConditions", "UnnecessaryLocalVariable"})
public class ListTest {
    public static void main(String[] args) {
        testInstanceof();
    }

    private static void testInstanceof() {
        Integer[] integers = {1,2,3};
        Object integers1 = integers;
        if (integers1 instanceof Integer[]){
            System.out.println("Integer[]");
        }
        if (integers1 instanceof int[]){
            System.out.println("int[]");
        }
        System.out.println("--------------");
        int[] int1 = {1,2,3};
        Object int2 = int1;
        if (int2 instanceof Integer[]){
            System.out.println("Integer[]");
        }
        if (int2 instanceof int[]){
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
