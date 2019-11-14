package com.ymy.receive;

import com.ymy.entity.User;
import com.ymy.utils.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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
    public void testQueueObject(User user){

        log.info("已经接收到消息，参数：{}",user);
    }

}
