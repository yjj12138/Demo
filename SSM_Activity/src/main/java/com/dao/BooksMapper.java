package com.dao;

import com.bean.Books;

import java.util.List;

public interface BooksMapper {
    //根据主键删除书籍
    int deleteByPrimaryKey(Integer bookid);

    int insert(Books record);
    //动态插入书籍
    int insertSelective(Books record);
    //根据主键查询
    Books selectByPrimaryKey(Integer bookid);
    //根据主键动态更新
    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKey(Books record);

    //查询需要导出的数据
    List<Books> findBooksByIds(int[] bids);
    //获取所有书籍信息
    List<Books> getAll();
    //批量删除书籍资料
    int deleteBooks(int[] bid);
}