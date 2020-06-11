package com.stone.manage.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stone.manage.admin.model.entity.SysUser;
import com.stone.manage.admin.model.entity.SysUserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 用户请求对象
 * @author wjj
 * @date 2020/6/1
 */
@Data
@ApiModel("用户请求体")
public class SysUserRequestDto {

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

    @ApiModelProperty("修改人")
    private String updateUser;

    @ApiModelProperty("用户角色")
    private List<SysUserRole> userRoles;

    /**
     * 实体转换
     * @param dto
     * @param entity
     * @return
     */
    public SysUser transferToEntity(SysUserRequestDto dto, SysUser entity) {
        Date now = new Date();
        if (Objects.isNull(entity)) {
            //新增
            entity.setCreateUser(dto.getCreateUser());
            entity.setCreateTime(now);
        } else {
            //修改
            entity.setUpdateUser(dto.getUpdateUser());
            entity.setUpdateTime(now);
        }
        entity.setUserName(StringUtils.isNotBlank(dto.getUserName())? dto.getUserName(): entity.getUserName());
        entity.setNickName(StringUtils.isNotBlank(dto.getNickName())? dto.getNickName(): entity.getNickName());
        entity.setAvatar(StringUtils.isNotBlank(dto.getAvatar())? dto.getAvatar(): entity.getAvatar());
        entity.setEmail(StringUtils.isNotBlank(dto.getEmail())? dto.getEmail(): entity.getEmail());
        entity.setMobile(StringUtils.isNotBlank(dto.getMobile())? dto.getMobile(): entity.getMobile());
        entity.setStatus(StringUtils.isNotBlank(dto.getStatus())? dto.getStatus(): entity.getStatus());
        entity.setDeptId(Objects.nonNull(dto.getDeptId())? dto.getDeptId(): entity.getDeptId());
        return entity;
    }
}
