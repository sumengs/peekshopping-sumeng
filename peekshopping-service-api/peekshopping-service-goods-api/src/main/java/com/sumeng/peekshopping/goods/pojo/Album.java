package com.sumeng.peekshopping.goods.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 图片表
 *
 * @date: 2020/6/6 20:18
 * @author: sumeng
 */
@Data
@Table(name = "tb_album")
public class Album implements Serializable {
    /**
     * 编号id
     */
    @Id
    private Long id;

    /**
     * 相册名称
     */
    private String title;

    /**
     * 相册封面
     */
    private String image;

    /**
     * 图片列表
     */
    private String imageItems;
}
