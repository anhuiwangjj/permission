package com.stone.manage.oauth.service.impl;

import com.stone.manage.core.dto.ResultDto;
import com.stone.manage.core.util.ResultUtil;
import com.stone.manage.oauth.service.PermissionService;
import com.stone.manage.oauth.vo.SysMenuVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限异常熔断
 * @author wjj
 * @date 2020/6/10
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Override
    public ResultDto<List<SysMenuVo>> getRolePermissionByRoleId(Long roleId) {
        return ResultUtil.handleFailure("调用getRolePermission失败");
    }
}
