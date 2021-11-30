package lq.test.bean1;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liqian477
 * @date 2021/11/22 18:17
 */
@Data
@Builder
public class BeanA implements Serializable {

    private static final long serialVersionUID = 408538029857496630L;
    private String name;
    private BeanInnerA inner;
}
