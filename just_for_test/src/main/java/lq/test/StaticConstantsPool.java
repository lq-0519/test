package lq.test;

/**
 * 测试常量池
 *
 * @author liqian477
 * @date 2022/01/10 11:02
 */
public class StaticConstantsPool {
    static int a = 1;
    static String s = "123";

    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(s);
    }
}
