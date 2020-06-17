package com.sumeng.peekshopping.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.sumeng.peekshopping.canal.config.RabbitMQConfig;
import com.sumeng.peekshopping.constant.GoodsStatus;
import com.sumeng.peekshopping.constant.MathNum;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/15 20:05
 * @author: sumeng
 */
@CanalEventListener
public class SpuListener {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 商品更改为上架状态的监听
     *
     * @param eventType evenType
     * @param rowData   rowData
     */
    @ListenPoint(schema = "peekshopping_goods", table = "tb_spu")
    public void goodUp(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {

        Map<String, String> oldData = new HashMap<>();

        for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
            oldData.put(column.getName(), column.getValue());
        }

        Map<String, String> newData = new HashMap<>();

        rowData.getAfterColumnsList().forEach((c) -> newData.put(c.getName(), c.getValue()));


        rowData.getAfterColumnsList().forEach((c) -> newData.put(c.getName(), c.getValue()));

        if (MathNum.zero.equals(oldData.get(GoodsStatus.IsMarketable)) && MathNum.one.equals(newData.get(GoodsStatus.IsMarketable))) {
            System.out.println("上架");
            rabbitTemplate.convertAndSend(RabbitMQConfig.GOOD_UP_EXCHANGE, "", newData.get("id"));
        }

        if (MathNum.one.equals(oldData.get(GoodsStatus.IsMarketable)) && MathNum.zero.equals(newData.get(GoodsStatus.IsMarketable))) {
            System.out.println("下架");
            rabbitTemplate.convertAndSend(RabbitMQConfig.GOOD_DOWN_EXCHANGE, "", newData.get("id"));
        }
    }
}
