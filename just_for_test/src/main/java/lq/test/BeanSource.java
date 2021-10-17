package lq.test;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liqian477
 * @date 2021/10/11 10:56
 */
@Data
public class BeanSource implements Serializable {
    private String name;
    private List<String> names;
}
