package com.stone.manage.core.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页返回对象
 * @author wjj
 * @date 2020/6/1
 */
@Data
@ApiModel("分页返回结果")
public class PageResult {

    @ApiModelProperty("当前页码")
    private int pageNum;

    @ApiModelProperty("每页数量")
    private int pageSize;

    @ApiModelProperty("总记录数")
    private long totalSize;

    @ApiModelProperty("总页数")
    private int totalPages;

    @ApiModelProperty("分页数据")
    private List<?> content;
}
