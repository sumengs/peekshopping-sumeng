package com.sumeng.peekshopping.canal.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @date: 2020/6/15 19:00
 * @author: sumeng
 */


@Configuration
public class RabbitMQConfig {

    /**
     * 更新队列名称
     */
    public static final String AD_UPDATE_QUEUE = "ad_update_queue";

    /**
     * 查询添加队列
     */
    public static final String SEARCH_ADD_QUEUE = "search_add_queue";


    /**
     * 查询下架队列
     */
    public static final String SEARCH_DEL_QUEUE = "search_del_queue";


    /**
     * 物品上架交换机
     */
    public static final String GOOD_UP_EXCHANGE = "good_up_exchange";


    /**
     * 物品上架交换机
     */
    public static final String GOOD_DOWN_EXCHANGE = "good_down_exchange";


    /**
     * 声明更新队列
     *
     * @return queue
     */
    @Bean(AD_UPDATE_QUEUE)
    public Queue adUpdateQueue() {
        return new Queue(AD_UPDATE_QUEUE);
    }


    /**
     * 声明查询添加队列
     *
     * @return queue
     */
    @Bean(SEARCH_ADD_QUEUE)
    public Queue searchAddQueue() {
        return new Queue(SEARCH_ADD_QUEUE);
    }


    /**
     * 声明查询删除队列
     *
     * @return queue
     */
    @Bean(SEARCH_DEL_QUEUE)
    public Queue searchDelQueue() {
        return new Queue(SEARCH_DEL_QUEUE);
    }

    /**
     * 声明交换机
     *
     * @return exchange
     */
    @Bean(GOOD_UP_EXCHANGE)
    public Exchange goodUpExchange() {
        return ExchangeBuilder.fanoutExchange(GOOD_UP_EXCHANGE).durable(true).build();
    }


    /**
     * 声明交换机
     *
     * @return exchange
     */
    @Bean(GOOD_DOWN_EXCHANGE)
    public Exchange goodDownExchange() {
        return ExchangeBuilder.fanoutExchange(GOOD_DOWN_EXCHANGE).durable(true).build();
    }


    /**
     * 交换机队列绑定
     *
     * @param queue    队列
     * @param exchange 交换机
     * @return 绑定信息
     */
    @Bean
    public Binding goodUpExchangeBinding(@Qualifier(SEARCH_ADD_QUEUE) Queue queue, @Qualifier(GOOD_UP_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }


    /**
     * 交换机队列绑定
     *
     * @param queue    队列
     * @param exchange 交换机
     * @return 绑定信息
     */
    @Bean
    public Binding goodDownExchangeBinding(@Qualifier(SEARCH_DEL_QUEUE) Queue queue, @Qualifier(GOOD_DOWN_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
}
