package com.ymy.receive;

import com.rabbitmq.client.Channel;
import com.ymy.utils.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class TopicExchangeConsumer {


    /**
     * 接收订阅消息的队列1
     */
    @RabbitListener(queues = RabbitConstant.TEST_QUEUE_TO_TOPIC1)
    public void test1(String content, Channel  channel , Message message) {

        log.info("这里是订阅队列1，已经接收到消息，参数：{}", content);

        try {
            //确认消息一被消费
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            //消息未呗消费
           // channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            log.info("已确认");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("确认失败！");
        }
    }

}
