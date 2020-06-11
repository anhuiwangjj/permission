package com.stone.manage.oauth.service;

import com.stone.manage.core.dto.ResultDto;
import com.stone.manage.oauth.service.impl.RoleServiceImpl;
import com.stone.manage.oauth.vo.SysRoleVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wjj
 * @date 2020/6/10
 */
@FeignClient(name = "manage-admin",fallback = RoleServiceImpl.class)
public interface RoleService {

    /**
     * 根据用户Id获取用户角色
     * @param userId
     * @return
     */
    @RequestMapping("sysRole/getRoleByUserId")
    ResultDto<List<SysRoleVo>> getRoleByUserId(@RequestParam(name = "userId") Long userId);
}
