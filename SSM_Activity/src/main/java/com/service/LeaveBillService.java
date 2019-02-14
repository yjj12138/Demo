package com.service;

import com.bean.Leavebill;

import java.util.List;

public interface LeaveBillService {
    //动态插入请假表
    int insertSelective(Leavebill record);
    //查询请假单
    List<Leavebill> getList(int uid);
    //动态修改请假单
    int updateByPrimaryKeySelective(Leavebill record);
    //通过主键查询请假单
    Leavebill selectByPrimaryKey(Integer id);
}
