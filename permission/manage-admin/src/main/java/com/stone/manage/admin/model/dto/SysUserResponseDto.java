package com.stone.manage.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stone.manage.admin.model.entity.SysUserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用户返回对象
 * @author wjj
 * @date 2020/6/1
 */
@Data
@ApiModel("系统用户返回对象")
public class SysUserResponseDto{

    @ApiModelProperty("用户ID")
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    private String mobile;

    @ApiModelProperty("有效状态")
    private String status;

    @ApiModelProperty("部门Id")
    private Long deptId;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("角色名称")
    private String roleNames;

    @ApiModelProperty("用户角色")
    private List<SysUserRole> userRoles;
}
