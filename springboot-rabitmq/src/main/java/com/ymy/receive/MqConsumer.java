package com.ymy.receive;

import com.rabbitmq.client.Channel;
import com.ymy.entity.User;
import com.ymy.utils.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class MqConsumer {

    /**
     * 接收普通消息队列，参数为字符串
     */
    @RabbitListener(queues = RabbitConstant.TEST_QUEUE)
    public void testQueue(String content){

        log.info("已经接收到消息，参数：{}",content);
    }

    /**
     * 接收普通消息队列，参数为对象
     */
    @RabbitListener(queues = RabbitConstant.TEST_QUEUE_OBJECT)
    public void testQueueObject(User user , Channel channel, Message message){

        log.info("已经接收到消息，参数：{}",user);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            log.info("已确认");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("确认失败！");
        }
    }

}
