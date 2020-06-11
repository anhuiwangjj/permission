package com.stone.manage.admin.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录请求体
 * @author wjj
 * @date 2020/6/5
 */
@Data
public class LoginRequestDto {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String captcha;
}
