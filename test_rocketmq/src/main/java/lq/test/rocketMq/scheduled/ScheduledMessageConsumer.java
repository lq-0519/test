package lq.test.rocketMq.scheduled;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author LQ
 * @create 2021-04-11 17:16
 */
public class ScheduledMessageConsumer {
    public static void main(String[] args) throws Exception {
        // 创建consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ExampleConsumer");
        // 订阅主题
        consumer.subscribe("lq_test_scheduled", "*");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        // 注册listener
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt messageExt : list) {
                    System.out.println("messageId = " + messageExt.getMsgId() + ", msgDelay = " + (System.currentTimeMillis() - messageExt.getStoreTimestamp()) + " ms");
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 开始消费
        consumer.start();
    }
}
