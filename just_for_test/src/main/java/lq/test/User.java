package lq.test;

import org.msgpack.annotation.Message;

import java.io.Serializable;
import java.util.List;

/**
 * 用户
 * 注意：需要序列化的 POJO 对象上必须加上 org.msgpack.annotation.Message 注解：@Message
 */
@Message
class User implements Serializable {

    private static final long serialVersionUID = 5842922210452658118L;
    private String pName;
    private List<User> users;

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "User{" +
                "pName='" + pName + '\'' +
                ", users=" + users +
                '}';
    }
}