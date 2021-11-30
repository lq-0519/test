package lq.test.inner.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liqian477
 * @date 2021/10/11 10:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeanTarget implements Serializable {

    private static final long serialVersionUID = 5550314347431701134L;
    private String name;
    //    private Data data;
    private int age;

    public static final class Builder {
        private String name;
        //    private Data data;
        private int age;

        private Builder() {
        }

        public static Builder aBeanTarget() {
            return new Builder();
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public BeanTarget build() {
            BeanTarget beanTarget = new BeanTarget();
            beanTarget.setName(name);
            beanTarget.setAge(age);
            return beanTarget;
        }
    }


//    @lombok.Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class Data implements Serializable {
//
//        private static final long serialVersionUID = -5956090180779638355L;
//        private String s;
//    }
}
