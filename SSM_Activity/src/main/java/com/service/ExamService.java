package com.service;

import com.bean.Department;
import com.bean.Exam;
import com.bean.Major;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExamService {
    //查询所有
    PageInfo<Exam> getAll(int pageindex,int pagesize,String ename);
    //查询学院
    List<Department> getDepart();
    //新增考试
    int insert(Exam record);
    //通过主键查询考试
    Exam selectByPrimaryKey(Integer examid);
    //通过主键动态修改考试
    int updateByPrimaryKeySelective(Exam record);
    //通过考试id查询考试信息和考试班级
    Exam getExamAndClasses(int examid);
    //通过主键删除考试
    int deleteByPrimaryKey(Integer examid);
    //考试成绩查询
    PageInfo getExamStudentAndScoresByEid(int examid,int pageindex,int pagesize,String stuname);
    //查询所有专业
    List<Major> getMajor();
    //动态新增考试
    int insertSelective(Exam record);
}
