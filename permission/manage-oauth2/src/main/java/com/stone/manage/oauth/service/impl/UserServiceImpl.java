package com.stone.manage.oauth.service.impl;

import com.stone.manage.core.dto.ResultDto;
import com.stone.manage.core.util.ResultUtil;
import com.stone.manage.oauth.service.UserService;
import com.stone.manage.oauth.vo.SysUserVo;
import org.springframework.stereotype.Service;

/**
 * 用户异常熔断
 * @author wjj
 * @date 2020/6/10
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public ResultDto<SysUserVo> findByUserName(String userName) {
        return ResultUtil.handleFailure("调用findByUsername接口失败");
    }
}
