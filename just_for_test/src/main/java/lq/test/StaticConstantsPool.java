package lq.test;

/**
 * 测试常量池
 *
 * @author liqian477
 * @date 2022/01/10 11:02
 */
public class StaticConstantsPool {
    static int a = 88;
    static String s = "123";

    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(s);
    }

    public int inc(int a) {
        int i = 99;
        a += i;
        return a;
    }
}
