package com.stone.manage.admin.service;


import com.stone.manage.admin.model.dto.SysUserRequestDto;
import com.stone.manage.admin.model.dto.SysUserResponseDto;
import com.stone.manage.admin.model.entity.SysUser;
import com.stone.manage.admin.model.entity.SysUserRole;
import com.stone.manage.core.page.PageRequest;
import com.stone.manage.core.service.CurdService;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * 用户Service接口
 * @author wjj
 * @date 2020/5/29
 */
public interface SysUserService extends CurdService<SysUser> {

    /**
     * 保存
     * @param dto
     */
    void save(SysUserRequestDto dto);

    /**
     * 修改
     * @param dto
     */
    void update(SysUserRequestDto dto);

    /**
     * 查询所有用户
     * @return
     */
    List<SysUser> findAll();

    /**
     * 根据用户名查找对象
     * @param userName
     * @return
     */
    SysUserResponseDto findByUserName(String userName);

    /**
     * 查找用户的菜单权限标识集合
     * @param userName
     * @return
     */
    Set<String> findPermissions(String userName);

    /**
     * 查找用户的角色集合
     * @param userId
     * @return
     */
    List<SysUserRole> findUserRoles(Long userId);

    /**
     * 生成用户信息Excel文件
     * @param pageRequest 要导出的分页查询参数
     * @return
     */
    File createUserExcelFile(PageRequest pageRequest);
}
