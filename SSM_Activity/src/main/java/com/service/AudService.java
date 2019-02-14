package com.service;

import com.bean.Classes;
import com.github.pagehelper.PageInfo;

public interface AudService {
    //查询所有
    PageInfo<Classes> getAll(int pageindex, int pagesize, String cname, String dname);
}
