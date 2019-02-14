package com.service.impl;

import com.bean.Information;
import com.bean.Infotype;
import com.dao.InformationMapper;
import com.dao.InfotypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationMapper informationMapper;
    @Autowired
    private InfotypeMapper infotypeMapper;
    //获取所有书籍资料及其资料类型(分页)
    @Override
    public PageInfo getAllBooks(int pageindex, int pagesize, String title) {
        PageHelper.startPage(pageindex,pagesize);
        Map map = new HashMap();
        map.put("title",title);
        List<Information> list = informationMapper.getAll(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
    //根据资料类型模糊查询书籍资料
    @Override
    public PageInfo mohu(int pageindex, int pagesize, int tid) {
        PageHelper.startPage(pageindex,pagesize);
        List<Information> mohu = informationMapper.mohu(tid);
        PageInfo pageInfo = new PageInfo(mohu);
        return pageInfo;
    }

    @Override
    public List<Infotype> getFileType() {
        return infotypeMapper.getAll();
    }

    @Override
    public int insertSelective(Information record) {
        return informationMapper.insertSelective(record);
    }

    @Override
    public Information getZiLiao(int id) {
        return informationMapper.getZiLiao(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Information record) {
        return informationMapper.updateByPrimaryKeySelective(record);
    }


}
