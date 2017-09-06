package com.cxy.common;

import java.util.List;

/**
 * Created by lidp on 2017/4/22.
 */
public class Pager {
    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    private List<?> list;
    private String total;
}
