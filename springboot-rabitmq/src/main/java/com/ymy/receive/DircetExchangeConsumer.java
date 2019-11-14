package com.ymy.receive;

import com.ymy.utils.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ding'yue模式（dircet）消费者
 */
@Component
@Slf4j
public class DircetExchangeConsumer {

    /**
     *接收订阅消息的队列1
     */
    @RabbitListener(queues = RabbitConstant.TEST_QUEUE_BIND_DIRECT1)
    public void test1(String content){

        log.info("这里是订阅队列1，已经接收到消息，参数：{}",content);
    }

    /**
     *接收订阅消息的队列2
     */
    @RabbitListener(queues = RabbitConstant.TEST_QUEUE_BIND_DIRECT2)
    public void test2(String content){

        log.info("这里是订阅队列2，已经接收到消息，参数：{}",content);
    }
}
