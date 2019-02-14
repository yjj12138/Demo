package com.dao;

import com.bean.Role;

import java.util.List;

public interface RoleMapper {
    //根据主键删除角色
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);
    //动态新增角色
    int insertSelective(Role record);

    //根据主键查询角色
    Role selectByPrimaryKey(Integer roleid);
    //动态修改角色信息
    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    //获取所有角色信息
    public List getAll();
    //查询单个角色详情
    public Role getRole(int roleid);

    //根据角色id删除中间表的角色信息
    public int deleteMiddleByRoleId(int roleid);

    //根据角色id查询关联的用户数
    public int selectusertbbyrid(int roleid);
}