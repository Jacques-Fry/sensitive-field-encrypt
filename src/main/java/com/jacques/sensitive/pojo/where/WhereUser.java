package com.jacques.sensitive.pojo.where;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户查询
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @since 2021/2/3 13:36
 */
@Getter
@Setter
public class WhereUser {
    /**
     * 页码
     */
    private int pageNo;
    /**
     * 每页个数
     */
    private int pageSize;
}
