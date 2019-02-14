package com.dao;

import com.bean.Information;

import java.util.List;
import java.util.Map;

public interface InformationMapper {
    int deleteByPrimaryKey(Integer informationid);

    int insert(Information record);
    //动态新增资料
    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer informationid);
    //根据主键动态更新资料信息
    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);
    //获取所有书籍资料和其对应的资料类型
    List<Information> getAll(Map map);
    //根据资料类型模糊查询书籍资料
    List<Information> mohu(int tid);
    //根据资料的id查询资料信息及其资料类型
    Information getZiLiao(int id);
}