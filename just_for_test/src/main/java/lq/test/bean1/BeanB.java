package lq.test.bean1;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liqian477
 * @date 2021/11/22 18:17
 */
@Data
public class BeanB implements Serializable {

    private static final long serialVersionUID = 7681149564371911866L;
    private String name;
    private BeanInnerB inner;
}
