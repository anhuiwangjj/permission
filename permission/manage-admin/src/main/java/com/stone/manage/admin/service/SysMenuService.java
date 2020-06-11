package com.stone.manage.admin.service;

import com.stone.manage.admin.model.dto.SysMenuRequestDto;
import com.stone.manage.admin.model.dto.SysMenuResponseDto;
import com.stone.manage.admin.model.entity.SysMenu;
import com.stone.manage.core.service.CurdService;

import java.util.List;

/**
 * 菜单service接口
 * @author wjj
 * @date 2020/6/2
 */
public interface SysMenuService extends CurdService<SysMenu> {

    /**
     * 保存
     * @param dto
     */
    void save(SysMenuRequestDto dto);

    /**
     * 修改
     * @param dto
     */
    void update(SysMenuRequestDto dto);

    /**
     * 根据用户名查找菜单列表
     * @param userName
     * @return
     */
    List<SysMenuResponseDto> findByUser(String userName);

    /**
     * 查询菜单树,用户ID和用户名为空则查询全部
     * @param menuType 获取菜单类型，0：获取所有菜单，包含按钮，1：获取所有菜单，不包含按钮
     * @param userName
     * @return
     */
    List<SysMenuResponseDto> findTree(String userName, String  menuType);
}
