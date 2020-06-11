package com.stone.manage.admin.dao;

import com.stone.manage.admin.model.dto.SysMenuResponseDto;
import com.stone.manage.admin.model.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单Mapper
 * @author wjj
 * @date 2020/6/2
 */
@Repository
public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    /**
     * 删除 更新标记
     * @param id
     */
    void updateDelFlag(Long id);

    /**
     * 分页
     * @return
     */
    List<SysMenuResponseDto> findPage();

    /**
     * 根据菜单名称查询分页
     * @param menuName
     * @return
     */
    List<SysMenuResponseDto> findPageByName(@Param(value="menuName") String menuName);

    /**
     * 查询所有菜单
     * @menuName
     */
    List<SysMenuResponseDto> findAll();

    /**
     * 根据用户名查询菜单
     * @param userName
     * @return
     */
    List<SysMenuResponseDto> findByUserName(@Param(value="userName") String userName);

    /**
     * 根据角色ID查询菜单
     * @param roleId
     * @return
     */
    List<SysMenuResponseDto> findRoleMenus(@Param(value="roleId") Long roleId);
}