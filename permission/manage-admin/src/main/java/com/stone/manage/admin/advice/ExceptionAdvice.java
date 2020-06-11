package com.stone.manage.admin.advice;

import com.stone.manage.core.dto.ResultDto;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.stone.manage.core.util.ResultUtil.handleException;

/**
 * 全局异常处理
 * @author wjj
 * @date 2020/5/29
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultDto<?> handleHttpJson(Exception e){
        return handleException(e,"json数据格式不正确，无法解析");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultDto<?> handleAssertException(Exception e){
        return handleException(e,e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultDto<?> handleCommonException(Exception e){
        return handleException(e,"操作失败");
    }
}
