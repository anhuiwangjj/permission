package com.stone.manage.oauth.service.impl;

import com.stone.manage.core.dto.ResultDto;
import com.stone.manage.oauth.service.PermissionService;
import com.stone.manage.oauth.service.RoleService;
import com.stone.manage.oauth.service.UserService;
import com.stone.manage.oauth.vo.SysMenuVo;
import com.stone.manage.oauth.vo.SysRoleVo;
import com.stone.manage.oauth.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author wjj
 * @date 2020/6/10
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResultDto<SysUserVo> userResult = userService.findByUserName(username);
        if (!Objects.equals("00",userResult.getCode())){
            throw new UsernameNotFoundException("用户:" + username + "不存在!");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        SysUserVo userVo = userResult.getData();
        ResultDto<List<SysRoleVo>> roleResult = roleService.getRoleByUserId(userVo.getId());
        if (Objects.equals("00",userResult.getCode())){
            List<SysRoleVo> roleVoList = roleResult.getData();
            for (SysRoleVo role: roleVoList){
                //角色必须是ROLE_开头，可以在数据库中设置
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
                grantedAuthorities.add(grantedAuthority);
                //获取权限
                ResultDto<List<SysMenuVo>> perResult  = permissionService.getRolePermissionByRoleId(role.getId());
                if (Objects.equals("00",perResult.getCode())){
                    List<SysMenuVo> permissionList = perResult.getData();
                    for (SysMenuVo menu:permissionList) {
                        GrantedAuthority authority = new SimpleGrantedAuthority(menu.getPermissions());
                        grantedAuthorities.add(authority);
                    }
                }
            }
        }
        User user = new User(userVo.getUserName(), userVo.getPassword(), grantedAuthorities);
        return user;
    }
}
