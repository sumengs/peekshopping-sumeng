package com.sumeng.peekshopping.search.listener;

import com.sumeng.peekshopping.search.config.RabbitMQConfig;
import com.sumeng.peekshopping.search.service.EsManagerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @date: 2020/6/17 14:40
 * @author: sumeng
 */
@Component
public class GoodsDownListener {

    @Autowired
    private EsManagerService esManagerService;


    @RabbitListener(queues = RabbitMQConfig.SEARCH_DEL_QUEUE)
    public void receiveMessage(String spuId) {
        System.out.println("接收到的消息为(下架)：" + spuId);

        //查询skuList并从索引库中删除
        esManagerService.delDataFromEsBySpuId(spuId);

    }
}
