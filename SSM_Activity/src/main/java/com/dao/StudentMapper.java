package com.dao;

import com.bean.Exam;
import com.bean.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer studentid);
    //新增学生
    int insert(Student record);

    int insertSelective(Student record);
    //通过主键查询学生
    Student selectByPrimaryKey(Integer studentid);
    //通过主键动态修改学生
    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    //查询所有学生
    List getAll(Map map);
    //查询学院
    List getDepart();
    //查询班级
    List getClasses(int majorid);
    //根据学生id查询信息和专业信息
    Student getStuAndMajor(int studentid);

    //考试成绩查询
    List<Student> getExamStudentAndScoresByEid(Map map);
}