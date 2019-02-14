package com.service.impl;

import com.bean.Classes;
import com.bean.Department;
import com.bean.Major;
import com.dao.ClassesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassesMapper classesMapper;
    //查询所有
    @Override
    public PageInfo<Classes> getAll(int pageindex, int pagesize, String cname, String dname) {
        //指定分页时的数据
        PageHelper.startPage(pageindex,pagesize);
        //调取dao层查询全部方法
        Map map = new HashMap();
        map.put("cname",cname);
        map.put("dname",dname);
        List list = classesMapper.getAll(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
    //查询学院
    @Override
    public List<Department> getDepart() {
        return classesMapper.getDepart();
    }
    //查询专业
    @Override
    public List<Major> getMajor(int departid) {
        return classesMapper.getMajor(departid);
    }
    //查询老师
    @Override
    public List getTeacher(int majorid) {
        return classesMapper.getTeacher(majorid);
    }
    //新增班级
    @Override
    public int insert(Classes record) {
        return classesMapper.insert(record);
    }
    //查询需要导出的数据信息
    @Override
    public List<Classes> findByIds(int[] cids) {
        return classesMapper.findByIds(cids);
    }

    @Override
    public Classes findClassesAndDepartmentByCid(int classid) {
        Classes classes = classesMapper.findClassesAndDepartmentByCid(classid);
        return classes;
    }
    //动态新增班级
    @Override
    public int insertSelective(Classes record) {
        return classesMapper.insertSelective(record);
    }
    //动态更新班级
    @Override
    public int updateByPrimaryKeySelective(Classes record) {
        return classesMapper.updateByPrimaryKeySelective(record);
    }
    //根据主键删除班级
    @Override
    public int deleteByPrimaryKey(Integer classid) {
        return classesMapper.deleteByPrimaryKey(classid);
    }

    @Override
    public List<Classes> getAllClasses() {
        return classesMapper.getAllClasses();
    }

    @Override
    public int changeState(int classid) {
        return classesMapper.changeState(classid);
    }

    @Override
    public int notThrough(int classid) {
        return classesMapper.notThrough(classid);
    }
}

