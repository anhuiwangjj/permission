package com.stone.manage.core.service;

import com.stone.manage.core.page.PageRequest;
import com.stone.manage.core.page.PageResult;

import java.util.List;

/**
 * 通用CURD接口
 * @author wjj
 * @date 2020/6/1
 */
public interface CurdService<T> {

    /**
     * 删除操作
     * @param record
     * @return
     */
    void delete(T record);

    /**
     * 批量删除操作
     * @param records
     * @return
     */
    void delete(List<T> records);

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 分页查询
     * @param pageRequest 统一分页查询请求
     * @return pageResult 统一分页查询结果
     */
    PageResult findPage(PageRequest pageRequest);
}
