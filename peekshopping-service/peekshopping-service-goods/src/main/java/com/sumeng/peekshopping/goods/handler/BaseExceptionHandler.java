package com.sumeng.peekshopping.goods.handler;

import com.sumeng.peekshopping.entity.Result;
import com.sumeng.peekshopping.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Array;

/**
 * 统一异常处理类
 *
 * @date: 2020/6/5 20:20
 * @author: sumeng
 */


@ControllerAdvice
public class BaseExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Array> error(Exception e) {
        e.printStackTrace();
        return new Result<>(false, StatusCode.ERROR, e.getMessage(), null);
    }
}
