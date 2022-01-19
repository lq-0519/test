package lq.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liqian477
 * @date 2021/8/2 17:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Man implements Serializable {

    private static final long serialVersionUID = -7240596588043512147L;

    private Integer age;

    private String name;

    public Man(String name) {
        this.name = name;
    }

    protected String showName() {
        return null;
    }
}
