package com.service;

import com.bean.Information;
import com.bean.Infotype;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface InformationService {

    //获取所有书籍资料及其资料类型(分页)
    PageInfo getAllBooks(int pageindex,int pagesize,String title);

    //根据资料类型模糊查询书籍资料
    PageInfo mohu(int pageindex,int pagesize,int tid);

    //获取所有书籍类型
    List<Infotype> getFileType();

    //动态新增资料
    int insertSelective(Information record);

    //根据资料的id查询资料信息及其资料类型
    Information getZiLiao(int id);

    //根据主键动态更新资料信息
    int updateByPrimaryKeySelective(Information record);
}
