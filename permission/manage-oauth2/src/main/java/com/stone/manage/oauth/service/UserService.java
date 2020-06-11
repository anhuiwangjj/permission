package com.stone.manage.oauth.service;

import com.stone.manage.core.dto.ResultDto;
import com.stone.manage.oauth.service.impl.UserServiceImpl;
import com.stone.manage.oauth.vo.SysUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wjj
 * @date 2020/6/10
 */
@FeignClient(name = "manage-admin",fallback = UserServiceImpl.class)
public interface UserService {

    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    @RequestMapping("sysUser/findByUserName")
    ResultDto<SysUserVo> findByUserName(@RequestParam(name = "userName") String userName);
}
