package com.jacques.sensitive.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页返回格式
 *
 * @info 分页返回格式
 * @version 1.0.0
 * @author Jacques·Fry
 * @since 2021/1/4 17:56
 */
public class PageResult<T> implements Serializable {

  private Long total;
  private List<T> data;

  public PageResult(Long total, List<T> rows) {
    super();
    this.total = total;
    this.data = rows;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }


}
