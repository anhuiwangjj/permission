package com.stone.manage.admin.model.dto;

import com.stone.manage.admin.model.entity.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * 菜单请求体
 * @author wjj
 * @date 2020/6/3
 */
@Data
@ApiModel("菜单请求对象")
public class SysMenuRequestDto {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("父级ID")
    private Long parentId;

    @ApiModelProperty("菜单url")
    private String url;

    @ApiModelProperty("权限")
    private String permissions;

    @ApiModelProperty("菜单类型")
    private String type;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("排序")
    private Integer orderNum;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("修改人")
    private String updateUser;

    /**
     * 新增实体转换
     * @param dto
     * @return
     */
    public SysMenu transferToEntity(SysMenuRequestDto dto) {
        SysMenu entity = new SysMenu();
        entity.setMenuName(dto.getMenuName());
        entity.setCreateUser(dto.getCreateUser());
        entity.setCreateTime(new Date());
        entity.setParentId(dto.getParentId());
        entity.setUrl(dto.getUrl());
        entity.setPermissions(dto.getPermissions());
        entity.setType(dto.getType());
        entity.setIcon(dto.getIcon());
        entity.setOrderNum(dto.getOrderNum());
        return entity;
    }

    /**
     * 修改实体转换
     * @param dto
     * @param entity
     * @return
     */
    public SysMenu transferToEntity(SysMenuRequestDto dto, SysMenu entity) {
        entity.setMenuName(StringUtils.isNotBlank(dto.getMenuName())? dto.getMenuName(): entity.getMenuName());
        entity.setUpdateUser(dto.getUpdateUser());
        entity.setUpdateTime(new Date());
        entity.setParentId(Objects.nonNull(dto.getParentId())? dto.getParentId(): entity.getParentId());
        entity.setUrl(StringUtils.isNotBlank(dto.getUrl())? dto.getUrl(): entity.getUrl());
        entity.setPermissions(StringUtils.isNotBlank(dto.getPermissions())? dto.getPermissions(): entity.getPermissions());
        entity.setType(StringUtils.isNotBlank(dto.getType())? dto.getType(): entity.getType());
        entity.setIcon(StringUtils.isNotBlank(dto.getIcon())? dto.getIcon(): entity.getIcon());
        entity.setOrderNum(Objects.nonNull(dto.getOrderNum())? dto.getOrderNum(): entity.getOrderNum());
        return entity;
    }
}
