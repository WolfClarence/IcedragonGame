package com.icedragongame.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gengxuelong
 * @date 2023/7/2 2:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页需要的dto")
public class PagingDto {
    @ApiModelProperty("第几页,从1开始")
    private int page_indices;
    @ApiModelProperty("每页的数量")
    private int page_num;
}
