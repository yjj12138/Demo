package com.dao;

import com.bean.Exam;
import com.bean.Major;

import java.util.List;
import java.util.Map;

public interface ExamMapper {
    //通过主键删除考试
    int deleteByPrimaryKey(Integer examid);
    //新增考试
    int insert(Exam record);
    //动态新增考试
    int insertSelective(Exam record);
    //通过主键查询考试
    Exam selectByPrimaryKey(Integer examid);
    //通过主键动态修改考试
    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);
    //查询所有
    List getAll(Map map);

    //查询学院
    List getDepart();

    //通过考试id查询考试信息和考试班级
    Exam getExamAndClasses(int examid);

    //考试成绩查询
    List<Exam> getExamStudentAndScoresByEid(Map map);

    //查询所有专业
    List<Major> getMajor();
}