package com.sumeng.peekshopping.system.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Resource实体类
 *
 * @date: 2020/6/9 15:30
 * @author: sumeng
 */
@Data
@Table(name = "tb_resource")
public class Resource implements Serializable {

    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * res_key
     */
    private String resKey;

    /**
     * res_name
     */
    private String resName;

    /**
     * parent_id
     */
    private Integer parentId;


}
