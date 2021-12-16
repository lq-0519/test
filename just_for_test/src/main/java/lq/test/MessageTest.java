package lq.test;


import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/25 0025.
 */
public class MessageTest {
    /**
     * 序列化与反序列化 String
     */
    public static void serializesString() {
        try {
            /** 创建序列化对象。提示：序列化是针对对象操作的
             * */
            String userInfo = "{\"pId\":9527,\"pName\":\"华安\",\"isMarry\":true}";

            /** org.msgpack.MessagePack 是 java 开发的基本类
             * 用于创建序列化器与反序列化器*/
            MessagePack messagePack = new MessagePack();

            /**序列化指定的对象为字节数组
             * 提示：这个字节数组与 java.lang.String#getBytes() 返回的字节数组是不一样的
             * 使用 String(byte bytes[]) 相应返回是得不到正常结果的
             * 只能再通过 MessagePack 进行反序列化得到结果*/
            byte[] raw = messagePack.write(userInfo);

            /**
             * <T> T read(byte[] bytes, Template<T> tmpl)
             * 根据模板将对象反序列化为对象，同一个 API 解决
             * 这个模板就是制定序列化的数据类型
             */
            String dst1 = messagePack.read(raw, Templates.TString);
            System.out.println(dst1);//输出：{"pId":9527,"pName":"华安","isMarry":true}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 序列化与反序列化 List<String>
     */
    public static void serializesStringList() {
        try {
            /** 创建序列化对象
             * 提示：序列化是针对对象操作的
             * */
            List<String> src = new ArrayList<String>();
            src.add("Java");
            src.add("IOS");
            src.add("Android");

            /** org.msgpack.MessagePack 是 java 开发的基本类
             * 用于创建序列化器与反序列化器
             */
            MessagePack messagePack = new MessagePack();

            /**序列化指定的对象为字节数组——————整个序列化过程就是如此简单，一个 API 解决
             * 提示：这个字节数组与 java.lang.String#getBytes() 返回的字节数组是不一样的
             * 使用 String(byte bytes[]) 相应返回是得不到正常结果的
             * 只能再通过 MessagePack 进行反序列化得到结果*/
            byte[] raw = messagePack.write(src);

            /**
             * <T> T read(byte[] bytes, Template<T> tmpl)
             * 根据模板将对象反序列化为对象
             * 这个模板就是制定序列化的数据类型
             */
            List<String> dst1 = messagePack.read(raw, Templates.tList(Templates.TString));
            System.out.println(dst1.get(0));//输出：Java
            System.out.println(dst1.get(1));//输出：IOS
            System.out.println(dst1.get(2));//输出：Android

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 序列化与反序列化 POJO
     * 注意：需要序列化的 POJO 对象上必须加上 org.msgpack.annotation.Message 注解：@Message
     */
    public static void serializesPOJO() {
        try {
            /** 创建序列化对象。提示：序列化是针对对象操作的
             * User 类上必须加上 @Message 注解
             * */
            User user = new User();
//            user.setpId(9527);
            user.setpName("华安");

            /** org.msgpack.MessagePack 是 java 开发的基本类
             * 用于创建序列化器与反序列化器
             */
            MessagePack messagePack = new MessagePack();

            /**序列化指定的对象为字节数组——————整个序列化过程就是如此简单，一个 API 解决
             * 提示：这个字节数组与 java.lang.String#getBytes() 返回的字节数组是不一样的
             * 使用 String(byte bytes[]) 相应返回是得不到正常结果的
             * 只能再通过 MessagePack 进行反序列化得到结果*/
            byte[] raw = messagePack.write(user);

            /** read(byte[] bytes, Class<T> c)
             * 将字节数组反序列化为指定类对象，c 指定 POJO 类即可
             */
            User userFinal = messagePack.read(raw, User.class);
            System.out.println(userFinal);
            //输出：User{birthday=null, pId=9527, pName='华安', isMarry=null}
//            System.out.println(userFinal.getpId() + "," + userFinal.getpName());
            //输出：9527,华安
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        serializesPOJO();
    }
}



