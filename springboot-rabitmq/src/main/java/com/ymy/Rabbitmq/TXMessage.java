package com.ymy.Rabbitmq;

import com.ymy.entity.User;
import com.ymy.utils.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@Slf4j
public class TXMessage  implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

    private RabbitTemplate rabbitTemplate;

    public TXMessage(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostConstruct
    public void initRabbitTemplate(){

        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 监听路由
     * @param correlationData
     * @param ack
     * @param s
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        if (ack) {
            log.info("消息已确认 cause:{} - {}" , s , correlationData.toString());
        } else {
            log.info("消息未确认 cause:{} - {}" , s , correlationData.toString());
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.info("消息被退回 {}" , message.toString());
    }

    @Transactional
    public void sendIngateQueue(User user) {
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData1 = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConstant.EXCHANGE_DIRECT,"direct_exchange_routing_key","hahahaha",correlationData1);
        log.info("进闸支付消息已发送 {}",user);

    }


}
