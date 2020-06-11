package com.stone.manage.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 菜单返回体
 * @author wjj
 * @date 2020/6/3
 */
@Data
@ApiModel("菜单返回对象")
public class SysMenuResponseDto {

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

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("父菜单名称")
    private String parentName;

    @ApiModelProperty("菜单级别")
    private Integer level;

    @ApiModelProperty("子菜单")
    private List<SysMenuResponseDto> children;
}
