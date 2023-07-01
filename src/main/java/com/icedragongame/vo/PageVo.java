package com.icedragongame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gengxuelong
 * @date 2023/7/2 2:54
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public  class PageVo<T> {
    private long all_num;
    private List<T> records;
}
