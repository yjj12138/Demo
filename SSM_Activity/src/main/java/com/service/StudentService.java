package com.service;

import com.bean.Department;
import com.bean.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService {
    //查询所有
    PageInfo<Student> getAll(int pageindex,int pagesize,String sname,String sno,String sgender);
    //查询学院
    List<Department> getDepart();
    //查询班级
    List getClasses(int majorid);
    //新增学生
    int insert(Student record);
    //通过主键查询学生
    Student selectByPrimaryKey(Integer studentid);
    //通过主键动态修改学生
    int updateByPrimaryKeySelective(Student record);
    //根据学生id查询信息和专业信息
    Student getStuAndMajor(int studentid);
    //考试成绩查询
    PageInfo getExamStudentAndScoresByEid(int examid,int pageindex,int pagesize,String stuname);
}
