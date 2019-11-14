package com.ymy;

import com.ymy.entity.User;
import com.ymy.utils.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringbootRabitmqApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送普通消息队列，参数为字符串
     */
    @Test
    void sendTestQueue() {

        rabbitTemplate.convertAndSend(RabbitConstant.TEST_QUEUE,"hello-bug");
        log.info("发送参数为字符串的普通消息完成！");
    }

    /**
     * 发送普通消息队列，参数为对象
     */
    @Test
    void sendTestQueueObject() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setAge(18);
        rabbitTemplate.convertAndSend(RabbitConstant.TEST_QUEUE_OBJECT,user);
        log.info("发送参数为对象的普通消息完成！");
    }

    /**
     * 发送订阅消息（direct）
     */
    @Test
    void sendDirect() {
        String content = "这是一条订阅消息（direct）";
        rabbitTemplate.convertAndSend(RabbitConstant.EXCHANGE_DIRECT,RabbitConstant.DIRECT_EXCHANGE_ROUTING_KEY,content);
        log.info("发送路由消息(direct)完成！");
    }


    /**
     * 发送订阅消息（fanout）
     */
    @Test
    void sendFanout() {
        String content = "这是一条订阅消息（fanout）";
        rabbitTemplate.convertAndSend(RabbitConstant.FANOUT_EXCHANGE,"",content);
        log.info("发送路由消息(fanout)完成！");
    }



    /**
     * 发送订阅消息（topic）
     */
    @Test
    void sendTopic() {
        String content = "这是一条订阅消息（topic）,路由key：topic.msg1";
        rabbitTemplate.convertAndSend(RabbitConstant.TOPIC_EXCHANGE,"topic.msg1",content);
        log.info("路由key：topic.msg1  发送完成！");

        content = "这是一条订阅消息（topic）,路由key：topic.msg2";
        rabbitTemplate.convertAndSend(RabbitConstant.TOPIC_EXCHANGE,"topic.msg2",content);
        log.info("路由key：topic.msg2  发送完成！");
    }


}
