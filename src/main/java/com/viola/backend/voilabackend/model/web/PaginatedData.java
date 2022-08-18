package com.viola.backend.voilabackend.model.web;

import java.util.List;

public class PaginatedData {
    List list;
    Long itemCount;
    public PaginatedData(List list, Long itemCount) {
        this.list = list;
        this.itemCount = itemCount;
    }
    public List getList() {
        return list;
    }
    public void setList(List list) {
        this.list = list;
    }
    public Long getItemCount() {
        return itemCount;
    }
    public void setItemCount(Long itemcount) {
        this.itemCount = itemcount;
    }

    
}
