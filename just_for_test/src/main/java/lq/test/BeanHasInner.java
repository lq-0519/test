package lq.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liqian477
 * @date 2021/11/19 13:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeanHasInner implements Serializable {

    private static final long serialVersionUID = -5799184418640158100L;
    private String name;
    private Inner inner;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Inner implements Serializable {

        private static final long serialVersionUID = -1654401695755663729L;
        private Integer age;
    }
}
