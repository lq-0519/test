package lq.test;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liqian477
 * @date 2021/10/11 10:57
 */
@Data
public class BeanTarget implements Serializable {

    private static final long serialVersionUID = 5550314347431701134L;
    private String name;
    private Data data;

    @lombok.Data
    static class Data implements Serializable {

        private static final long serialVersionUID = -5956090180779638355L;
        private String s;
    }
}
