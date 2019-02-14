package com.service.impl;

import com.bean.Classes;
import com.dao.ClassesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.AudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AudServiceImol implements AudService {
    @Autowired
    private ClassesMapper classesMapper;
    @Override
    public PageInfo<Classes> getAll(int pageindex, int pagesize, String cname, String dname) {
        //指定分页时的数据
        PageHelper.startPage(pageindex,pagesize);
        //调取dao层查询全部方法
        Map map = new HashMap();
        map.put("cname",cname);
        map.put("dname",dname);
        List list = classesMapper.getall(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
