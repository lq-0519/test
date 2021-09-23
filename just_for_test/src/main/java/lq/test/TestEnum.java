package lq.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liqian477
 * @date 2021/9/23 10:37
 */
public enum TestEnum {
    /**
     * 正常
     */
    ENUM0(0, "0"),

    /**
     * 复制中
     */
    ENUM1(1, "1"),

    /**
     * 副本
     */
    ENUM2(2, "2"),

    ;
    private static final Map<Integer, TestEnum> ENUM_MAP = new HashMap<>(4);

    static {
        TestEnum[] values = TestEnum.values();
        for (TestEnum value : values) {
            ENUM_MAP.put(value.getStatus(), value);
        }
    }

    private final Integer status;
    private final String desc;

    TestEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static TestEnum parseOf(Integer status) {
        return ENUM_MAP.get(status);
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
