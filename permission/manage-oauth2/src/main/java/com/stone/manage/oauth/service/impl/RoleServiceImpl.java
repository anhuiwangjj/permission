package com.stone.manage.oauth.service.impl;

import com.stone.manage.core.dto.ResultDto;
import com.stone.manage.core.util.ResultUtil;
import com.stone.manage.oauth.service.RoleService;
import com.stone.manage.oauth.vo.SysRoleVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色异常熔断
 * @author wjj
 * @date 2020/6/10
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public ResultDto<List<SysRoleVo>> getRoleByUserId(Long userId) {
        return ResultUtil.handleFailure("调用getRoleByUserId失败");
    }
}
