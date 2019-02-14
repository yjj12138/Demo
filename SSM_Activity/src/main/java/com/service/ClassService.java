package com.service;

import com.bean.Classes;
import com.bean.Department;
import com.bean.Major;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClassService {
    //查询所有
    PageInfo<Classes> getAll(int pageindex,int pagesize,String cname,String dname);
    //查询学院
    List<Department> getDepart();
    //查询专业
    List<Major> getMajor(int departid);
    //查询老师
    List getTeacher(int majorid);
    //添加班级
    int insert(Classes record);
    //查询需要导出的数据信息
    List<Classes> findByIds(int[] cids);
    //根据班级id查询班级以及所属学院信息
    Classes findClassesAndDepartmentByCid(int classid);
    //动态新增班级
    int insertSelective(Classes record);
    //动态修改班级
    int updateByPrimaryKeySelective(Classes record);
    //根据主键删除班级
    int deleteByPrimaryKey(Integer classid);
    //无条件查询所有班级
    List<Classes> getAllClasses();
    //修改班级状态(通过)
    int changeState(int classid);
    //修改班级状态(驳回)
    int notThrough(int classid);

}
