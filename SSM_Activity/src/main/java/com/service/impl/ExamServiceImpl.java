package com.service.impl;

import com.bean.Department;
import com.bean.Exam;
import com.bean.Major;
import com.dao.ExamMapper;
import com.dao.MajorMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;
    @Override
    public PageInfo<Exam> getAll(int pageindex, int pagesize, String ename) {
        PageHelper.startPage(pageindex,pagesize);
        Map map = new HashMap();
        map.put("ename",ename);
        List list = examMapper.getAll(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
    //查询学院
    @Override
    public List<Department> getDepart() {
        return examMapper.getDepart();
    }
    //新增考试
    @Override
    public int insert(Exam record) {
        return examMapper.insert(record);
    }

    @Override
    public Exam selectByPrimaryKey(Integer examid) {
        return examMapper.selectByPrimaryKey(examid);
    }

    @Override
    public int updateByPrimaryKeySelective(Exam record) {
        return examMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Exam getExamAndClasses(int examid) {
        return examMapper.getExamAndClasses(examid);
    }

    @Override
    public int deleteByPrimaryKey(Integer examid) {
        return examMapper.deleteByPrimaryKey(examid);
    }

    @Override
    public PageInfo getExamStudentAndScoresByEid(int examid,int pageindex,int pagesize,String stuname) {
        PageHelper.startPage(pageindex,pagesize);
        Map map = new HashMap();
        map.put("stuname",stuname);
        map.put("examid",examid);
        List<Exam> examList = examMapper.getExamStudentAndScoresByEid(map);
        PageInfo pageInfo = new PageInfo(examList);
        return pageInfo;
    }

    @Override
    public List<Major> getMajor() {
        return examMapper.getMajor();
    }

    @Override
    public int insertSelective(Exam record) {
        return examMapper.insertSelective(record);
    }


}
