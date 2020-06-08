package com.sumeng.peekshopping.goods.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 活动表
 *
 * @date: 2020/6/6 20:21
 * @author: sumeng
 */
@Table(name = "tb_pref")
@Data
public class Pref implements Serializable {

    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 分类id
     */
    private Integer cateId;

    /**
     * 消费金额
     */
    private Integer buyMoney;

    /**
     * 优惠金额
     */
    private Integer preMoney;

    /**
     * 活动开始日期
     */
    private java.util.Date startTime;

    /**
     * 活动截至日期
     */
    private java.util.Date endTime;

    /**
     * 类型
     */
    private String type;

    /**
     * 状态
     */
    private String state;


}
