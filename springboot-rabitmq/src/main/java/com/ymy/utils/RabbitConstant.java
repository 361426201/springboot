package com.ymy.utils;

/**
 * 消息队列的常量
 */
public class RabbitConstant {

    /**
     * 普通的消息队列，参数为字符串
     */
    public static final String TEST_QUEUE = "test_queue";

    /**
     * 普通的消息队列，参数为对象
     */
    public static final String TEST_QUEUE_OBJECT = "test_queue_object";


    /**
     * 交换机----direct
     */
    public static final String EXCHANGE_DIRECT = "exchange_direct";


    /**
     * 绑定到叫环境（direct）的普通队列
     */
    public static final String TEST_QUEUE_BIND_DIRECT1 = "test_queue_bind_direct1";


    /**
     * 绑定到叫环境（direct）的普通队列
     */
    public static final String TEST_QUEUE_BIND_DIRECT2 = "test_queue_bind_direct2";


    /**
     * 交换机（direct）的路由key
     */
    public static final String DIRECT_EXCHANGE_ROUTING_KEY = "direct_exchange_routing_key";

    /**
     * 普通队列1（用户绑定到交换机fanout上）
     */
    public static final String TEST_QUEUE_TO_FANOUT1 = "test_queue_to_fanout1";

    /**
     * 普通队列2（用户绑定到交换机fanout上）
     */
    public static final String TEST_QUEUE_TO_FANOUT2 = "test_queue_to_fanout2";

    /**
     * 交换机（fanout）
     */
    public static final String FANOUT_EXCHANGE = "fanout_exchange";



    /**
     * 普通队列1（用户绑定到交换机topic上）
     */
    public static final String TEST_QUEUE_TO_TOPIC1 = "topic.message1";


    /**
     * 普通队列2（用户绑定到交换机topic上）
     */
    public static final String TEST_QUEUE_TO_TOPIC2 = "topic.message2";

    /**
     * 交换机（topic）
     */
    public static final String TOPIC_EXCHANGE = "topic_exchange";


    /**
     * 路由key（topic）
     */
    public static final String TOPIC_EXCHANGE_ROUTING_KEY = "topic.#";

}
