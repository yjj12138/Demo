package com.dao;

import com.bean.Infotype;

import java.util.List;

public interface InfotypeMapper {
    int deleteByPrimaryKey(Integer infoid);

    int insert(Infotype record);

    int insertSelective(Infotype record);

    Infotype selectByPrimaryKey(Integer infoid);

    int updateByPrimaryKeySelective(Infotype record);

    int updateByPrimaryKey(Infotype record);

    //获取所有资料类型
    List<Infotype> getAll();
}