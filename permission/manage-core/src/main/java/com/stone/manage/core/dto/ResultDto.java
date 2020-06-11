package com.stone.manage.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用返回对象
 * @author wjj
 * @date 2020/5/29
 */
@ApiModel("通用返回对象")
@Data
public class ResultDto<T> {

    @ApiModelProperty("数据返回对象")
    private T data;

    @ApiModelProperty("状态码")
    private String code;

    @ApiModelProperty("返回信息")
    private String msg;

    @ApiModelProperty("异常信息")
    private String exception;

    @ApiModelProperty("额外返回对象")
    private Object extras;
}
