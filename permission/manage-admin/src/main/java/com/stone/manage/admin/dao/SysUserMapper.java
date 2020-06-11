package com.stone.manage.admin.dao;

import com.stone.manage.admin.model.dto.SysUserResponseDto;
import com.stone.manage.admin.model.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 查询全部
     * @return
     */
    List<SysUser> findAll();

    /**
     * 分页查询
     * @return
     */
    List<SysUserResponseDto> findPage();

    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    SysUserResponseDto findByUserName(String userName);

    /**
     * 根据用户名分页
     * @param userName
     * @return
     */
    List<SysUserResponseDto> findPageByUserName(@Param(value="userName") String userName);

    /**
     * 根据用户名邮箱分页
     * @param userName
     * @param email
     * @return
     */
    List<SysUserResponseDto> findPageByUserNameAndEmail(@Param(value="userName") String userName, @Param(value="email") String email);

    /**
     * 删除修改删除标记
     * @param id
     */
    void updateDelFlag(Long id);
}