package com.stone.manage.core.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stone.manage.common.utils.ReflectionUtils;

import java.util.List;

/**
 * Mybatis分页查询助手
 * @author wjj
 * @date 2020/6/1
 */
public class MybatisPageHelper {

    /**
     * 约定查询方法名 findPage
     */
    public static final String findPage = "findPage";

    /**
     * 分页查询
     * @param pageRequest 分页请求
     * @param mapper dao接口，mybatis的mapper
     * @return
     */
    public static PageResult findPage(PageRequest pageRequest,Object mapper){
        return findPage(pageRequest, mapper, findPage);
    }

    /**
     * 调用分页插件进行分页查询
     * @param pageRequest 分页请求体
     * @param mapper dao接口，mybatis的Mapper
     * @param queryMethodName 分页的查询方法名
     * @param args 方法参数
     * @return
     */
    @SuppressWarnings({"unchecked","rawTypes"})
    public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName, Object... args) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        //利用反射调用查询方法
        Object result = ReflectionUtils.invoke(mapper,queryMethodName,args);
        return getPageResult(new PageInfo<>((List) result));
    }

    /**
     * 分页返回对象组装
     * @param pageInfo 分页信息
     * @return
     */
    public static PageResult getPageResult(PageInfo<?> pageInfo){
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
