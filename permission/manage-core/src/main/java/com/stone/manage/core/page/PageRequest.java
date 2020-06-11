package com.stone.manage.core.page;

import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * 分页请求参数
 * @author wjj
 * @date 2020/6/1
 */
@Data
@ApiModel("分页请求对象")
public class PageRequest {

    @ApiModelProperty("当前页码")
    private int pageNum = 1;

    @ApiModelProperty("每页数量")
    private int pageSize = 10;

    @ApiModelProperty("查询参数")
    private Map<String,Object> params = Maps.newHashMap();

    /**
     * 获取查询参数
     * @param key
     * @return
     */
    public Object getParam(String key){
        return getParams().get(key);
    }
}
