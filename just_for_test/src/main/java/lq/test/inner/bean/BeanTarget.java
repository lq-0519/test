package lq.test.inner.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liqian477
 * @date 2021/10/11 10:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeanTarget implements Serializable {

    private static final long serialVersionUID = 5550314347431701134L;
    private String name;
    //    private Data data;
    private int age;

//    @lombok.Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class Data implements Serializable {
//
//        private static final long serialVersionUID = -5956090180779638355L;
//        private String s;
//    }
}
