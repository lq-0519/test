package lq.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liqian477
 * @date 2021/12/13 20:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeanDouble {
    private String name;
    private List<BeanDouble> beanDoubles;
}
