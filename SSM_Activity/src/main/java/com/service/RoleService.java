package com.service;

import com.bean.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {
    //获取所有角色信息
    public PageInfo getAll(int pageindex,int pagesize);

    //获取所有角色信息(用于分页)
    public List getAll();

    //获取所有角色信息(无分页)
    public List getAllRoles();

    //查询单个角色详情
    public Role getRole(int roleid);

    //动态修改角色信息
    int updateByPrimaryKeySelective(Role record);

    //根据主键查询角色
    Role selectByPrimaryKey(Integer roleid);

    //修改角色信息
    public int updateRole(int[] menuid,Role role);

    //根据角色id查询关联的用户数
    public int selectusertbbyrid(int roleid);

    //根据主键删除角色
    int deleteByPrimaryKey(Integer roleid);

}
