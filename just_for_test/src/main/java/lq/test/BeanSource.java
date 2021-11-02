package lq.test;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liqian477
 * @date 2021/10/11 10:56
 */
@Data
public class BeanSource implements Serializable {

    private static final long serialVersionUID = -65853199328056047L;
    private String name;
    private Data data;
    private Integer age;

    @lombok.Data
    static class Data implements Serializable {

        private static final long serialVersionUID = -8626807112072660643L;
        private String s;
    }
}
