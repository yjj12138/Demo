package com.service.impl;

import com.bean.Department;
import com.bean.Exam;
import com.bean.Student;
import com.dao.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    //查询所有
    @Override
    public PageInfo<Student> getAll(int pageindex, int pagesize, String sname, String sno, String sgender) {
        PageHelper.startPage(pageindex,pagesize);
        Map map = new HashMap();
        map.put("sname",sname);
        map.put("sno",sno);
        map.put("sgender",sgender);
        List list = studentMapper.getAll(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
    //查询学院
    @Override
    public List<Department> getDepart() {
        return studentMapper.getDepart();
    }
    //查询班级
    @Override
    public List getClasses(int majorid) {
        return studentMapper.getClasses(majorid);
    }
    //新增学生
    @Override
    public int insert(Student record) {
        return studentMapper.insert(record);
    }

    @Override
    public Student selectByPrimaryKey(Integer studentid) {
        return studentMapper.selectByPrimaryKey(studentid);
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return studentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Student getStuAndMajor(int studentid) {
        return studentMapper.getStuAndMajor(studentid);
    }

    @Override
    public PageInfo getExamStudentAndScoresByEid(int examid, int pageindex, int pagesize, String stuname) {
        PageHelper.startPage(pageindex,pagesize);
        Map map = new HashMap();
        map.put("stuname",stuname);
        map.put("examid",examid);
        List<Student> stulist = studentMapper.getExamStudentAndScoresByEid(map);
        PageInfo pageInfo = new PageInfo(stulist);
        return pageInfo;
    }
}
