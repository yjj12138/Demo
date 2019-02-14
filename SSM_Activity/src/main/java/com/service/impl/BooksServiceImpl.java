package com.service.impl;

import com.bean.Books;
import com.dao.BooksMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BooksServiceImpl implements BooksService {
    @Autowired
    private BooksMapper booksMapper;
    //查询需要导出的数据信息
    @Override
    public List<Books> findBooksByIds(int[] bid) {
        return booksMapper.findBooksByIds(bid);
    }

    @Override
    public PageInfo getAll(int pageindex, int pagesize) {
        PageHelper.startPage(pageindex,pagesize);
        List<Books> list = booksMapper.getAll();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int deleteBooks(int[] bid) {
        return booksMapper.deleteBooks(bid);
    }

    @Override
    public int updateByPrimaryKeySelective(Books record) {
        return booksMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Books selectByPrimaryKey(Integer bookid) {
        return booksMapper.selectByPrimaryKey(bookid);
    }

    @Override
    public int insertSelective(Books record) {
        return booksMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer bookid) {
        return booksMapper.deleteByPrimaryKey(bookid);
    }


}
