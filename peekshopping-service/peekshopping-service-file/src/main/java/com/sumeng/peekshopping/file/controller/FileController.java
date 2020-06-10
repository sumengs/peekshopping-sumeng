package com.sumeng.peekshopping.file.controller;

import com.sumeng.peekshopping.entity.Result;
import com.sumeng.peekshopping.entity.StatusCode;
import com.sumeng.peekshopping.file.pojo.FastDfsFile;
import com.sumeng.peekshopping.file.util.FastDfsClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @date: 2020/6/8 21:02
 * @author: sumeng
 */


@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 上传成功的url
     */
    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file) {
        try {
            //判断文件是否存在
            if (file == null) {
                throw new RuntimeException("文件不存在");
            }
            //获取文件完整的名
            String originalFilename = file.getOriginalFilename();
            if (StringUtils.isEmpty(originalFilename)) {
                throw new RuntimeException("文件不存在");
            }

            //获取文件扩展名称 abc.jpg
            String extName = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);

            //获取文件内容
            byte[] content = file.getBytes();

            //创建文件上传的封装实体类
            FastDfsFile fastDfsFile = new FastDfsFile(originalFilename, content, extName);

            //基于工具类进行文件上传,并接受参数
            String[] uploadResult = FastDfsClient.upload(fastDfsFile);

            //封装返回结果
            //http://47.97.218.10:8888/group1/M00/00/00/rBD0CF7eJ3WAcSmtAAFrTYRq4PM110.jpg
            String url = FastDfsClient.getTrackerUrl() + uploadResult[0] + "/" + uploadResult[1];

            return new Result<>(true, StatusCode.OK, "上传成功", url);


        } catch (Exception e) {
            e.printStackTrace();

        }
        return new Result<>(false, StatusCode.ERROR, "文件上传失败", "");
    }
}
