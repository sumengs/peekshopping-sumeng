package com.sumeng.peekshopping.search.listener;

import com.sumeng.peekshopping.search.config.RabbitMQConfig;
import com.sumeng.peekshopping.search.service.EsManagerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @date: 2020/6/17 12:57
 * @author: sumeng
 */
@Component
public class GoodsUpListener {


    @Autowired
    private EsManagerService esManagerService;


    @RabbitListener(queues = RabbitMQConfig.SEARCH_ADD_QUEUE)
    public void receiveMessage(String spuId) {
        System.out.println("接收到的消息为(上架)：" + spuId);

        //查询skuList并导入索引库
        esManagerService.importDataToEsBySpuId(spuId);

    }
}
