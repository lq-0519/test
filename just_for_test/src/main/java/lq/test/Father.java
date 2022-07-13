package lq.test;

/**
 * @author liqian477
 * @date 2022/06/24 16:05
 */
abstract public class Father {

    public String getStr() {
        return "father";
    }

    public String getValue() {
        return getStr();
    }

    public void getClassName() {
        String name = this.getClass().getName();
        System.out.println("name = " + name);
        String simpleName = this.getClass().getSimpleName();
        System.out.println("simpleName = " + simpleName);
        String canonicalName = this.getClass().getCanonicalName();
        System.out.println("canonicalName = " + canonicalName);
        String typeName = this.getClass().getTypeName();
        System.out.println("typeName = " + typeName);
    }
}
