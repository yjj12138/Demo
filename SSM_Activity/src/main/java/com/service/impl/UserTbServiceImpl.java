package com.service.impl;

import com.bean.Menu;
import com.bean.UserTb;
import com.dao.MenuMapper;
import com.dao.UserTbMapper;
import com.service.UserTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTbServiceImpl implements UserTbService {
    @Autowired
    private UserTbMapper userTbMapper;
    @Autowired
    private MenuMapper menuMapper;
    //登录
    @Override
    public UserTb login(UserTb userTb) {
        UserTb u = userTbMapper.login(userTb.getUserName());
        if (u != null && u.getUserPs().equals(userTb.getUserPs())) {
            //根据角色查询菜单信息
            List<Menu> menus = menuMapper.getMenuByRid(u.getRoleId());
            //分一二级菜单
            List oneList = new ArrayList();//用于存储一级菜单信息
            for (Menu menu : menus) {
                if(menu.getUpmenuid() == -1) {
                    List secondList = new ArrayList();//用于存储一级菜单对应的二级菜单信息
                    for (Menu menu1 : menus) {
                        if(menu1.getUpmenuid() == menu.getMenuid()) {
                            secondList.add(menu1);
                        }
                    }
                    //建立一级菜单对象和二级菜单集合的关系
                    menu.setSecond(secondList);
                    oneList.add(menu);
                }
            }
            u.getRole().setMenu(oneList);
            if(u.getStudentId() != null) {
                int teacherid = userTbMapper.findTeacherIdByUid(u.getUserId());
                u.setTeacherid(teacherid);
            }
            return u;
        }
        return null;
    }
    //通过主键动态更新用户
    @Override
    public int updateByPrimaryKeySelective(UserTb record) {
        return userTbMapper.updateByPrimaryKeySelective(record);
    }
    //根据主键查询
    @Override
    public UserTb selectByPrimaryKey(Integer userId) {
        return userTbMapper.selectByPrimaryKey(userId);
    }
    //查询需要导出的数据信息
    @Override
    public List<UserTb> findUsertbByIds(int[] uids) {
        return userTbMapper.findUsertbByIds(uids);
    }

    @Override
    public int insertSelective(UserTb record) {
        return userTbMapper.insertSelective(record);
    }

    @Override
    public int deleteUsers(int[] uid) {
        return userTbMapper.deleteUsers(uid);
    }

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return userTbMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public UserTb selectOneUsertbById(int userId) {
        return userTbMapper.selectOneUsertbById(userId);
    }


}
