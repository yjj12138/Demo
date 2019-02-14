package com.dao;

import com.bean.Classes;

import java.util.List;
import java.util.Map;

public interface ClassesMapper {
    //根据主键删除班级
    int deleteByPrimaryKey(Integer classid);

    int insert(Classes record);
    //动态新增班级
    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer classid);
    //动态修改班级
    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    //查询全部
    List getAll(Map map);
    //班级审核
    List getall(Map map);
    //查询学院
    List getDepart();
    //查询专业
    List getMajor(int departid);
    //查询老师
    List getTeacher(int majorid);
    //查询需要导出的数据信息
    List<Classes> findByIds(int[] cids);
    //根据班级id查询班级以及所属学院信息和专业信息
    Classes findClassesAndDepartmentByCid(int classid);
    //无条件查询所有班级
    List<Classes> getAllClasses();
    //修改班级状态(通过)
    int changeState(int classid);
    //修改班级状态(驳回)
    int notThrough(int classid);
}