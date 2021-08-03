package lq.test;

import java.io.Serializable;
import java.util.Map;

/**
 * @author liqian477
 * @date 2021/8/2 17:40
 */
public class Man implements Serializable {
    private Integer age;

    private String name;

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Man{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
