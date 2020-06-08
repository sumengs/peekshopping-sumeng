package com.sumeng.peekshopping.file.pojo;

import lombok.Data;

/**
 * FastDFS实体类
 *
 * @date: 2020/6/8 20:04
 * @author: sumeng
 */

@Data
public class FastDfsFile {


    /**
     * 文件名称
     */
    private String name;


    /**
     * 文件内容
     */
    private byte[] content;


    /**
     * 文件扩展名
     */
    private String ext;


    /**
     * 文件MD5摘要值
     */
    private String author;

    public FastDfsFile(String name, byte[] content, String ext, String height,
                       String width, String author) {
        super();
        this.name = name;
        this.content = content;
        this.ext = ext;
        this.author = author;
    }

    public FastDfsFile(String name, byte[] content, String ext) {
        super();
        this.name = name;
        this.content = content;
        this.ext = ext;
    }
}
