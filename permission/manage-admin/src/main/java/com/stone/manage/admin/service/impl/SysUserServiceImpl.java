package com.stone.manage.admin.service.impl;

import com.stone.manage.admin.constant.SysConstants;
import com.stone.manage.admin.dao.SysMenuMapper;
import com.stone.manage.admin.dao.SysUserMapper;
import com.stone.manage.admin.dao.SysUserRoleMapper;
import com.stone.manage.admin.model.dto.SysMenuResponseDto;
import com.stone.manage.admin.model.dto.SysUserRequestDto;
import com.stone.manage.admin.model.dto.SysUserResponseDto;
import com.stone.manage.admin.model.entity.SysUser;
import com.stone.manage.admin.model.entity.SysUserRole;
import com.stone.manage.admin.service.SysUserService;
import com.stone.manage.admin.util.PasswordUtils;
import com.stone.manage.core.page.MybatisPageHelper;
import com.stone.manage.core.page.PageRequest;
import com.stone.manage.core.page.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 用户Service实现
 * @author wjj
 * @date 2020/5/29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }

    @Override
    public void save(SysUserRequestDto dto) {
        // 新增用户
        SysUserResponseDto responseDto = sysUserMapper.findByUserName(dto.getUserName());
        Assert.notNull(responseDto,"用户名已存在!");
        SysUser sysUser = dto.transferToEntity(dto,null);
        String salt = PasswordUtils.getSalt();
        String password = PasswordUtils.encode(dto.getPassword(), salt);
        sysUser.setSalt(salt);
        sysUser.setPassword(password);
        sysUserMapper.insertSelective(sysUser);
        if (!CollectionUtils.isEmpty(dto.getUserRoles())) {
            dto.getUserRoles().forEach(v -> {
                v.setUserId(sysUser.getId());
                sysUserRoleMapper.insertSelective(v);
            });
        }
    }

    @Override
    public void update(SysUserRequestDto dto) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(dto.getId());
        if(Objects.nonNull(sysUser)) {
            Assert.isTrue(!SysConstants.ADMIN.equalsIgnoreCase(dto.getUserName()),"超级管理员不允许修改!");
        }

        sysUser = dto.transferToEntity(dto,sysUser);
        String salt = PasswordUtils.getSalt();
        // 修改用户, 且修改了密码
        if(!Objects.equals(dto.getPassword(),sysUser.getPassword())) {
            String password = PasswordUtils.encode(dto.getPassword(), salt);
            sysUser.setSalt(salt);
            sysUser.setPassword(password);
        }
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (!CollectionUtils.isEmpty(dto.getUserRoles())) {
            //先删除原有的用户角色关系重新新增
            sysUserRoleMapper.deleteByUserId(sysUser.getId());
            for (SysUserRole userRole: dto.getUserRoles()) {
                userRole.setUserId(sysUser.getId());
                sysUserRoleMapper.insertSelective(userRole);
            }
        }
    }

    @Override
    public void delete(SysUser record) {
        sysUserMapper.updateDelFlag(record.getId());
    }

    @Override
    public void delete(List<SysUser> records) {
        records.forEach(v -> delete(v));
    }

    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult pageResult = null;
        Object userName = pageRequest.getParam("userName");
        Object email = pageRequest.getParam("email");
        if (Objects.nonNull(userName)){
            if (Objects.nonNull(email)) {
                pageResult = MybatisPageHelper.findPage(pageRequest,sysUserMapper,"findPageByUserNameAndEmail",userName,email);
            } else {
                pageResult = MybatisPageHelper.findPage(pageRequest,sysUserMapper,"findPageByUserName",userName);
            }
        } else {
            pageResult = MybatisPageHelper.findPage(pageRequest,sysUserMapper);
        }
        return pageResult;
    }

    @Override
    public SysUserResponseDto findByUserName(String userName) {
        return sysUserMapper.findByUserName(userName);
    }

    @Override
    public Set<String> findPermissions(String userName) {
        Set<String> perms = new HashSet<>();
        List<SysMenuResponseDto> sysMenus = sysMenuMapper.findByUserName(userName);
        for(SysMenuResponseDto sysMenu:sysMenus) {
            if (StringUtils.isNotBlank(sysMenu.getPermissions())) {
                perms.add(sysMenu.getPermissions());
            }
        }
        return perms;
    }

    @Override
    public List<SysUserRole> findUserRoles(Long userId) {
        return null;
    }

    @Override
    public File createUserExcelFile(PageRequest pageRequest) {
        return null;
    }
}
