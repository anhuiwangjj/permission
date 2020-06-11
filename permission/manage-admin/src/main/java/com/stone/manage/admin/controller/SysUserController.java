package com.stone.manage.admin.controller;


import com.stone.manage.admin.constant.SysConstants;
import com.stone.manage.admin.model.dto.SysUserRequestDto;
import com.stone.manage.admin.model.dto.SysUserResponseDto;
import com.stone.manage.admin.model.entity.SysUser;
import com.stone.manage.admin.service.SysUserService;
import com.stone.manage.core.dto.ResultDto;
import com.stone.manage.core.page.PageRequest;
import com.stone.manage.core.page.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.stone.manage.core.util.ResultUtil.handleSuccess;

/**
 * 用户Controller
 * @author wjj
 * @date 2020/5/29
 */
@Api(value = "用户操作接口",tags = {"用户操作接口"})
@RestController
@RequestMapping("sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "查询所有用户",notes = "查询所有用户")
    @GetMapping("findAll")
    public ResultDto<List<SysUser>> findAll() {
        return handleSuccess(sysUserService.findAll());
    }

    @ApiOperation(value = "分页查询",notes = "分页查询")
    @PostMapping("findPage")
    public ResultDto<PageResult> findPage(@RequestBody PageRequest pageRequest){
        return handleSuccess(sysUserService.findPage(pageRequest));
    }

    @ApiOperation(value = "保存用户",notes = "保存用户")
    @PostMapping("save")
    public ResultDto<?> save(@RequestBody SysUserRequestDto dto){
        sysUserService.save(dto);
        return handleSuccess("保存成功");
    }

    @ApiOperation(value = "修改用户",notes = "修改用户")
    @PostMapping("update")
    public ResultDto<?> update(@RequestBody SysUserRequestDto dto){
        sysUserService.update(dto);
        return handleSuccess("修改成功");
    }

    @ApiOperation(value = "删除用户",notes = "删除用户")
    @PostMapping("delete")
    public ResultDto<?> delete(@RequestBody List<SysUser> records){
        records.forEach(v -> {
            SysUser sysUser = sysUserService.findById(v.getId());
            if (Objects.nonNull(sysUser)) {
                Assert.isTrue(!SysConstants.ADMIN.equalsIgnoreCase(sysUser.getUserName()),"超级管理员不允许删除!");
            }
        });
        sysUserService.delete(records);
        return handleSuccess("删除成功");
    }

    @ApiOperation(value = "根据用户名查询",notes = "根据用户名查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名", dataType = "string",paramType = "query")
    })
    @GetMapping("findByUserName")
    public ResultDto<SysUserResponseDto> findByUserName(@RequestParam(name="userName") String userName){
        Assert.hasText(userName,"用户名不能为空");
        return handleSuccess(sysUserService.findByUserName(userName));
    }
}
