package lq.test;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liqian477
 * @date 2021/10/29 17:26
 */
@Data
public class Bean implements Serializable {

    private static final long serialVersionUID = 3346790493072137482L;
    private String a;

    @Data
    class InnerBean implements Serializable {

        private static final long serialVersionUID = -365743667924402840L;
        private String innerB;
    }
}
