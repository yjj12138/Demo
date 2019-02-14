package com.service.impl;

import com.bean.Department;
import com.dao.DepartmentMapper;
import com.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    //查询所有学院
    @Override
    public List<Department> getAll() {
        return departmentMapper.getAll();
    }
}
