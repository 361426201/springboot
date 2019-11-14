package com.ymy.receive;

import com.ymy.utils.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicExchangeConsumer {


    /**
     * 接收订阅消息的队列1
     */
    @RabbitListener(queues = RabbitConstant.TEST_QUEUE_TO_TOPIC1)
    public void test1(String content) {

        log.info("这里是订阅队列1，已经接收到消息，参数：{}", content);
    }

}
