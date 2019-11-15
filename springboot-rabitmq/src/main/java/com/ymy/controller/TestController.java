package com.ymy.controller;

import com.ymy.Rabbitmq.TXMessage;
import com.ymy.entity.User;
import com.ymy.utils.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    private RabbitTemplate rabbitTemplate;

    private TXMessage txMessage;

    @Autowired
    public  TestController(RabbitTemplate rabbitTemplate, TXMessage txMessage){
        this.rabbitTemplate = rabbitTemplate;
        this.txMessage = txMessage;
    }

    /**
     * 发送普通消息队列，参数为字符串
     */
    @GetMapping(value = "sendTestQueue")
    public String sendTestQueue() {

        rabbitTemplate.convertAndSend(RabbitConstant.TEST_QUEUE,"hello-bug");
        log.info("发送参数为字符串的普通消息完成！");

        return "Success!!!!";
    }

    /**
     * 发送普通消息队列，参数为对象
     */
    @GetMapping(value = "sendTestQueueObject")
    public String sendTestQueueObject() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setAge(18);
        rabbitTemplate.convertAndSend(RabbitConstant.TEST_QUEUE_OBJECT,user);
        log.info("发送参数为对象的普通消息完成！");

        return "Success!!!!";
    }

    /**
     * 发送订阅消息（direct）
     */
    @GetMapping(value = "sendDirect")
    public String sendDirect() {
        String content = "这是一条订阅消息（direct）";
        rabbitTemplate.convertAndSend(RabbitConstant.EXCHANGE_DIRECT,RabbitConstant.DIRECT_EXCHANGE_ROUTING_KEY,content);
        log.info("发送路由消息(direct)完成！");

        return "Success!!!!";
    }


    /**
     * 发送订阅消息（fanout）
     */
    @GetMapping(value = "sendFanout")
    public String sendFanout() {
        String content = "这是一条订阅消息（fanout）";
        rabbitTemplate.convertAndSend(RabbitConstant.FANOUT_EXCHANGE,"",content);
        log.info("发送路由消息(fanout)完成！");
        return "Success!!!!";
    }



    /**
     * 发送订阅消息（topic）
     */
    @GetMapping(value = "sendTopic1")
    public String sendTopic1() {
        String content1 = "这是一条订阅消息（topic）,路由key：topic.message1";
        rabbitTemplate.convertAndSend(RabbitConstant.TOPIC_EXCHANGE,"topic.message1",content1);
        log.info("路由key：topic.msg1  发送完成！");

        return "Success!!!!";
    }



    /**
     * 事务消息
     */
    @GetMapping(value = "sendTXMsg")
    public String sendTXMsg() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setAge(18);
        txMessage.sendIngateQueue(user);
        log.info("需要确认的消息发送完成");

        return "Success!!!!";
    }

}
