package lq.test;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liqian477
 * @date 2021/9/8 11:17
 */
@Data
public class People implements Serializable {

    private static final long serialVersionUID = -4813698136978969830L;

    private Integer age;

    private String name;
}
