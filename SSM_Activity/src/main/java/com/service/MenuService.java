package com.service;

import com.bean.Menu;
import com.bean.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MenuService {
    //获取所有用户角色信息
    public PageInfo getAll(int pageindex,int pagesize);
    //获取菜单信息(分页)
    public PageInfo getmenus(int pageindex,int pagesize);
    //批量删除菜单
    public int deleteMenus(String[] mid);
    //通过id查询菜单信息
    public List<Menu> findMenusByMids(int[] mid);
    //获取所有菜单信息
    public List getAllMenus();
    //新增角色
    public int addRoles(Role role,int[] menuid);

    //获取所有一级菜单
    public List getAllFirstMenu();

    //动态新增菜单
    int insertSelective(Menu record);

    //根据主键查询菜单信息
    Menu selectByPrimaryKey(Integer menuid);

    //根据主键删除单个菜单信息
    int deletemenubymenuid(int menuid,int upmenuid);

    //通过主键动态修改菜单
    int updateMenuByPrimaryKeySelective(Menu record,int oldupmenuid);
}
