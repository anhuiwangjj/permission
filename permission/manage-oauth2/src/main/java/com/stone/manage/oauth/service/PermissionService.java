package com.stone.manage.oauth.service;

import com.stone.manage.core.dto.ResultDto;
import com.stone.manage.oauth.service.impl.PermissionServiceImpl;
import com.stone.manage.oauth.vo.SysMenuVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wjj
 * @date 2020/6/10
 */
@FeignClient(name = "manage-admin",fallback = PermissionServiceImpl.class)
public interface PermissionService {

    /**
     * 根据角色Id获取权限
     * @param roleId
     * @return
     */
    @RequestMapping("sysRole/getRolePermission")
    ResultDto<List<SysMenuVo>> getRolePermissionByRoleId(@RequestParam(name = "roleId") Long roleId);
}
