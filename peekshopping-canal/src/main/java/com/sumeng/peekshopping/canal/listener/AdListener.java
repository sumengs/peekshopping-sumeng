package com.sumeng.peekshopping.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.sumeng.peekshopping.canal.config.RabbitMQConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @date: 2020/6/13 20:10
 * @author: sumeng
 */
@CanalEventListener
public class AdListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @ListenPoint(schema = "peekshopping_business", table = {"tb_ad"})
    public void adUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        List<CanalEntry.Column> columnsList = rowData.getAfterColumnsList();
        for (CanalEntry.Column column : columnsList) {
            if ("position".equals(column.getName())) {
                System.out.println("发送最新的数据到MQ：" + column.getValue());
                //发送信息
                rabbitTemplate.convertAndSend("", RabbitMQConfig.AD_UPDATE_QUEUE, column.getValue());
            }
        }


    }
}