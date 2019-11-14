package com.ymy.config;

import com.ymy.utils.RabbitConstant;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 创建消息队列，一定要创建，不然在启动消费者服务器的时候会报错，找不到这个队列
 */
@Component
public class RabbitConfig {


    /**
     * 创建一个发送字符串的普通消息
     * 参数1 name ：队列名
     * 参数2 durable ：是否持久化
     * 参数3 exclusive ：仅创建者可以使用的私有队列，断开后自动删除
     * 参数4 autoDelete : 当所有消费客户端连接断开后，是否自动删除队列
     * @return
     */
    @Bean
    public Queue testQueue(){

        return new Queue(RabbitConstant.TEST_QUEUE,true,false,false);
    }

    /**
     * 创建一个普通的消息队列，一定要创建，不然在启动消费者服务器的时候会报错，找不到这个队列
     * @return
     */
    @Bean
    public Queue testQueueToobject(){

        return new Queue(RabbitConstant.TEST_QUEUE_OBJECT,true,false,false);
    }


    /**
     * 订阅模式----dircet
     *参数1 name ：交互器名
     * 参数2 durable ：是否持久化
     * 参数3 autoDelete ：当所有消费客户端连接断开后，是否自动删除队列
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitConstant.EXCHANGE_DIRECT,true,false);
    }

    /**
     * 需要绑定到交换机（direct）上的普通队列
     * @return
     */
    @Bean
    public Queue testQueuebindDircet1(){

        return new Queue(RabbitConstant.TEST_QUEUE_BIND_DIRECT1,true,false,false);
    }
    /**
     * 需要绑定到交换机（direct）上的普通队列
     * @return
     */
    @Bean
    public Queue testQueuebindDircet2(){

        return new Queue(RabbitConstant.TEST_QUEUE_BIND_DIRECT2,true,false,false);
    }

    /**
     * 将普通队列绑定到交换机（direct）上
     * @return
     */
    @Bean
    public Binding binding1() {
        //链式写法: 用指定的路由键将队列绑定到交换机
        return BindingBuilder.bind(testQueuebindDircet1()).to(directExchange()).with(RabbitConstant.DIRECT_EXCHANGE_ROUTING_KEY);
    }

    /**
     * 将普通队列绑定到交换机（direct）上
     * @return
     */
    @Bean
    public Binding binding2() {
        //链式写法: 用指定的路由键将队列绑定到交换机
        return BindingBuilder.bind(testQueuebindDircet2()).to(directExchange()).with(RabbitConstant.DIRECT_EXCHANGE_ROUTING_KEY);
    }

    /**
     * 创建普通的消息队列1（用户绑定到交换机Fanout上）
     * @return
     */
    @Bean
    public Queue testQueuetoFanout1(){

        return new Queue(RabbitConstant.TEST_QUEUE_TO_FANOUT1,true,false,false);
    }

    /**
     * 创建普通的消息队列2（用户绑定到交换机Fanout上）
     * @return
     */
    @Bean
    public Queue testQueuetoFanout2(){

        return new Queue(RabbitConstant.TEST_QUEUE_TO_FANOUT2,true,false,false);
    }

    /**
     * 交换机（fanout）
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitConstant.FANOUT_EXCHANGE,true,false);
    }


    /**
     * 将普通队列1绑定到叫环境（direct）上
     * @return
     */
    @Bean
    public Binding bindToFanout1() {
        //链式写法: 用指定的路由键将队列绑定到交换机
        return BindingBuilder.bind(testQueuetoFanout1()).to(fanoutExchange());
    }

    /**
     * 将普通队列2绑定到叫环境（direct）上
     * @return
     */
    @Bean
    public Binding bindToFanout2() {
        //链式写法: 用指定的路由键将队列绑定到交换机
        return BindingBuilder.bind(testQueuetoFanout2()).to(fanoutExchange());
    }







    /**
     * 创建普通的消息队列1（用户绑定到交换机topic上）
     * @return
     */
    @Bean(name = "msg1")
    public Queue testQueuetoTopic1(){

        return new Queue(RabbitConstant.TEST_QUEUE_TO_TOPIC1,true,false,false);
    }

    /**
     * 创建普通的消息队列2（用户绑定到交换机topic上）
     * @return
     */
    @Bean(name = "msg2")
    public Queue testQueuetoTopic2(){

        return new Queue(RabbitConstant.TEST_QUEUE_TO_TOPIC2,true,false,false);
    }

    /**
     * 交换机（topic）
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitConstant.TOPIC_EXCHANGE,true,false);
    }


    /**
     * 将普通队列1绑定到叫环境（Topic）上
     * @return
     */
    @Bean
    public Binding bindingTopic1(@Qualifier("msg1")Queue queueMessages,TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with(RabbitConstant.TOPIC_EXCHANGE_ROUTING_KEY);
    }

    /**
     * 将普通队列2绑定到叫环境（Topic）上
     * @return
     */
    @Bean
    public Binding bindingTopic2(@Qualifier("msg2")Queue queueMessages, TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with(RabbitConstant.TOPIC_EXCHANGE_ROUTING_KEY);
    }









}
