package com.dao;

import com.bean.Leavebill;

import java.util.List;

public interface LeavebillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Leavebill record);
    //动态插入请假表
    int insertSelective(Leavebill record);
    //通过主键查询请假单
    Leavebill selectByPrimaryKey(Integer id);
    //动态修改请假单
    int updateByPrimaryKeySelective(Leavebill record);

    int updateByPrimaryKey(Leavebill record);

    //查询请假单
    List<Leavebill> getList(int uid);
}