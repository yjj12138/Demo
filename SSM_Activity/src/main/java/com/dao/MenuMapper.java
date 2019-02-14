package com.dao;

import com.bean.Menu;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    //通过主键删除菜单信息
    int deleteByPrimaryKey(Integer menuid);

    int insert(Menu record);
    //动态新增菜单
    int insertSelective(Menu record);
    //根据主键查询菜单信息
    Menu selectByPrimaryKey(Integer menuid);
    //通过主键动态修改菜单
    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    //根据角色id查询菜单信息
    public List<Menu> getMenuByRid(int roleid);

    //获取所有用户角色信息
    public List getAll();
    //获取菜单信息
    public List getmenus();
    //批量删除菜单
    public int deleteMenus(List mids);
    //通过id查询菜单信息
    public List<Menu> findMenusByMids(int[] mid);
    //新增角色和菜单的关系
    public int addMiddle(Map map);
    //根据角色id查询菜单信息
    public List<Menu> getMenusByRid(int roleid);
    //查询多个菜单被角色使用的个数(通过菜单id)
    public int selectCountByMid(List mids);
    //根据多个一级菜单id查询其下的二级菜单数量
    public int selectSecondCountByMids(List mids);
    //获取所有一级菜单
    public List getAllFirstMenu();
    //查询单个菜单被角色使用的个数(通过菜单id)
    public int selectonecountbymid(int menuid);
    //根据单个一级菜单id查询其下的二级菜单数量
    public int selectOneSecondCountByMid(int menuid);

}