package lq.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liqian477
 * @date 2021/8/2 17:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Man1 implements Serializable {

    private static final long serialVersionUID = -7240596588043512147L;

    private Integer age;

    private String name;

    private List<Man1> manList;

    public Man1(String name) {
        this.name = name;
    }
}
