package com.sumeng.peekshopping.goods.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 日志
 *
 * @date: 2020/6/6 20:20
 * @author: sumeng
 */
@Table(name = "undo_log")
@Data
public class Log implements Serializable {

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 品牌id
     */
    private Long branchId;

    /**
     * xid
     */
    private String xid;
    /**
     * 回滚信息
     */
    private byte[] rollbackInfo;

    /**
     * 日志状态
     */
    private Integer logStatus;

    /**
     * 日志创建
     */
    private java.util.Date logCreated;

    /**
     * 日志修改
     */
    private java.util.Date logModified;

    /**
     * ext
     */
    private String ext;
}
