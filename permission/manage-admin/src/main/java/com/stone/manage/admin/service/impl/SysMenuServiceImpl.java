package com.stone.manage.admin.service.impl;

import com.google.common.collect.Lists;
import com.stone.manage.admin.constant.SysConstants;
import com.stone.manage.admin.dao.SysMenuMapper;
import com.stone.manage.admin.model.dto.SysMenuRequestDto;
import com.stone.manage.admin.model.dto.SysMenuResponseDto;
import com.stone.manage.admin.model.entity.SysMenu;
import com.stone.manage.admin.service.SysMenuService;
import com.stone.manage.core.page.MybatisPageHelper;
import com.stone.manage.core.page.PageRequest;
import com.stone.manage.core.page.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * 菜单Service实现
 * @author wjj
 * @date 2020/6/2
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public void save(SysMenuRequestDto dto) {
        SysMenu sysMenu = dto.transferToEntity(dto);
        sysMenuMapper.insertSelective(sysMenu);
    }

    @Override
    public void update(SysMenuRequestDto dto) {
        SysMenu entity = sysMenuMapper.selectByPrimaryKey(dto.getId());
        if (Objects.isNull(dto.getParentId())) {
            dto.setParentId(0L);
        }
        entity = dto.transferToEntity(dto,entity);
        sysMenuMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<SysMenuResponseDto> findByUser(String userName) {
        if(StringUtils.isBlank(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)) {
            return sysMenuMapper.findAll();
        }
        return sysMenuMapper.findByUserName(userName);
    }

    @Override
    public void delete(SysMenu record) {
        sysMenuMapper.updateDelFlag(record.getId());
    }

    @Override
    public void delete(List<SysMenu> records) {
        records.forEach(v -> delete(v));
    }

    @Override
    public SysMenu findById(Long id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,sysMenuMapper);
    }

    @Override
    public List<SysMenuResponseDto> findTree(String userName, String menuType) {
        List<SysMenuResponseDto> sysMenus = Lists.newArrayList();
        List<SysMenuResponseDto> menus = findByUser(userName);
        for (SysMenuResponseDto menu : menus) {
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menu.setLevel(0);
                if(!exists(sysMenus, menu)) {
                    sysMenus.add(menu);
                }
            }
        }
        sysMenus.sort(Comparator.comparing(SysMenuResponseDto::getOrderNum));
        findChildren(sysMenus, menus, menuType);
        return sysMenus;
    }

    private void findChildren(List<SysMenuResponseDto> sysMenus,List<SysMenuResponseDto> menus,String menuType) {
        for (SysMenuResponseDto sysMenu: sysMenus){
            List<SysMenuResponseDto> children = Lists.newArrayList();
            for (SysMenuResponseDto menu: menus){
                if (Objects.equals(menuType,"1") && Objects.equals(menu.getType(),"2")){
                    //过滤菜单是按钮类型的
                    continue;
                }
                if(Objects.nonNull(sysMenu.getId()) && Objects.equals(sysMenu.getId(),menu.getParentId())){
                    menu.setParentName(sysMenu.getMenuName());
                    menu.setLevel(sysMenu.getLevel() + 1);
                    if (!exists(children,menu)){
                        children.add(menu);
                    }
                }
            }
            sysMenu.setChildren(children);
            children.sort(Comparator.comparing(SysMenuResponseDto::getOrderNum));
            findChildren(children,menus,menuType);
        }
    }

    private boolean exists(List<SysMenuResponseDto> sysMenus,SysMenuResponseDto sysMenu){
        boolean exist = false;
        for (SysMenuResponseDto menu: sysMenus) {
            if (Objects.equals(menu.getId(),sysMenu.getId())){
                exist = true;
            }
        }
        return exist;
    }
}
