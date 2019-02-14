package com.service;

import com.bean.Books;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BooksService {
    //查询需要导出的数据信息
    List<Books> findBooksByIds(int[] bids);
    //展示数据(分页)
    PageInfo getAll(int pageindex, int pagesize);
    //批量删除书籍资料
    int deleteBooks(int[] bid);
    //根据主键动态更新
    int updateByPrimaryKeySelective(Books record);

    //根据主键查询
    Books selectByPrimaryKey(Integer bookid);

    //动态插入书籍
    int insertSelective(Books record);

    //根据主键删除书籍
    int deleteByPrimaryKey(Integer bookid);
}
