package lq.test.inner.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liqian477
 * @date 2021/10/11 10:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeanSource implements Serializable {

    private static final long serialVersionUID = -65853199328056047L;
    private String name;
    //    private Data data;
    private Integer age;

//    @lombok.Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class Data implements Serializable {
//
//        private static final long serialVersionUID = -8626807112072660643L;
//        private String s;
//    }
}
