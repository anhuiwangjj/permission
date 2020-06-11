package com.stone.manage.core.util;

import com.stone.manage.core.dto.ResultDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 返回体工具类
 * @author wjj
 * @date 2020/5/29
 */
public class ResultUtil {

    private static final Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    public static<T> ResultDto<T> handleFailure(String message){
        ResultDto<T> result = new ResultDto<>();
        message = StringUtils.isNotBlank(message)?  message : "失败";
        result.setCode("01");
        result.setMsg(message);
        return result;
    }

    public static ResultDto<?> handleException(Exception e, String message){
        return handleException(e,new ResultDto<>(), message);
    }

    public static ResultDto<?> handleException(Exception e,ResultDto<?> result,String message){
        if (e instanceof IllegalArgumentException) {
            message = e.getMessage();
        }
        message = StringUtils.isNotBlank(message)?  message : "失败";
        result.setCode("01");
        result.setMsg(message);
        result.setException(e.getClass().getName()+":"+e.getMessage());
        logger.error(message,e);
        return result;
    }

    public static <T> ResultDto<T> handleSuccess(){
        return handleSuccess(new ResultDto<>(),null,"");
    }

    public static <T> ResultDto<T> handleSuccess(String message) {
        return handleSuccess(new ResultDto<>(), null, message);
    }

    public static <T> ResultDto<T> handleSuccess(T data) {
        return handleSuccess(new ResultDto<>(), data, "");
    }

    public static <T> ResultDto<T> handleSuccess(T data, String message){
        return handleSuccess(new ResultDto<>(), data, message);
    }

    public static <T> ResultDto<T> handleSuccess(T data, Object extras, String message){
        return handleSuccess(new ResultDto<>(), data, extras, message);
    }

    public static <T> ResultDto<T> handleSuccess(ResultDto<T> result, T data, String message){
        message = StringUtils.isNotBlank(message)? message : "成功";
        result.setCode("00");
        if (Objects.nonNull(data)) {
            result.setData(data);
        }
        result.setMsg(message);
        return result;
    }

    public static <T> ResultDto<T> handleSuccess(ResultDto<T> result, T data, Object extras, String message){
        message = StringUtils.isNotBlank(message)? message : "成功";
        result.setCode("00");
        if (Objects.nonNull(data)) {
            result.setData(data);
        }
        if (Objects.nonNull(extras)) {
            result.setExtras(extras);
        }
        result.setMsg(message);
        return result;
    }
}
