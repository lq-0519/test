package lq.test;

/**
 * 测试常量池
 *
 * @author liqian477
 * @date 2022/01/10 11:02
 */
public class StaticConstantsPool {
    static int a = 88;

    static {
        a = 77;
        System.out.println("a = " + a);
    }

    static String s = "123";

    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(s);
        String[] strings = new String[10];
//        strings[1]="123";
        System.out.println(strings.length);
    }

    public int inc(int a) {
        int i = 99;
        a += i;
        return a;
    }
}
