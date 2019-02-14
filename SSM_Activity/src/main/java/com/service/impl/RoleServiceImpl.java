package com.service.impl;

import com.bean.Menu;
import com.bean.Role;
import com.dao.MenuMapper;
import com.dao.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    //获取所有角色信息(分页)
    @Override
    public PageInfo getAll(int pageindex, int pagesize) {
        PageHelper.startPage(pageindex,pagesize);
        List list = roleMapper.getAll();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List getAll() {
        return roleMapper.getAll();
    }

    @Override
    public List getAllRoles() {
        return roleMapper.getAll();
    }

    //查询单个角色详情
    @Override
    @Transactional
    public Role getRole(int roleid) {
        Role role = roleMapper.getRole(roleid);
        List<Menu> m = menuMapper.getMenuByRid(roleid);
        //分一二级菜单
        List oneList = new ArrayList();//用于存储一级菜单信息
        for (Menu menu : m) {
            if(menu.getUpmenuid() == -1) {
                List secondList = new ArrayList();//用于存储一级菜单对应的二级菜单信息
                for (Menu menu1 : m) {
                    if(menu1.getUpmenuid() == menu.getMenuid()) {
                        secondList.add(menu1);
                    }
                }
                //建立一级菜单对象和二级菜单集合的关系
                menu.setSecond(secondList);
                oneList.add(menu);
            }
        }
        role.setMenu(oneList);
        return role;
    }
    //动态修改角色信息
    @Override
    @Transactional
    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }
    //根据主键查询角色
    @Override
    @Transactional
    public Role selectByPrimaryKey(Integer roleid) {
        return roleMapper.selectByPrimaryKey(roleid);
    }

    @Override
    @Transactional
    public int updateRole(int[] menuid, Role role) {
        //先更新角色表中信息
        roleMapper.updateByPrimaryKeySelective(role);
        //修改角色的菜单列表(先删除后添加)
        //删除中间表中角色id对应的菜单信息
        roleMapper.deleteMiddleByRoleId(role.getRoleid());
        //将新的菜单信息添加到中间表中
        Map map = new HashMap();
        map.put("roleid",role.getRoleid());
        map.put("menuid",menuid);
        int i = menuMapper.addMiddle(map);
        return i;
    }

    @Override
    public int selectusertbbyrid(int roleid) {
        return roleMapper.selectusertbbyrid(roleid);
    }

    @Override
    public int deleteByPrimaryKey(Integer roleid) {
        return roleMapper.deleteByPrimaryKey(roleid);
    }

}
