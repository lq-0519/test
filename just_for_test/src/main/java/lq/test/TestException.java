package lq.test;

/**
 * @author liqian477
 * @date 2021/9/22 14:21
 */
public class TestException extends RuntimeException {
    private String code;

    public TestException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
