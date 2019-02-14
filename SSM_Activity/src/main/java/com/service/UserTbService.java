package com.service;

import com.bean.UserTb;

import java.util.List;

public interface UserTbService {
    //登录方法
    UserTb login(UserTb userTb);
    //根据主键动态修改用户
    int updateByPrimaryKeySelective(UserTb record);
    //根据主键查询所有
    UserTb selectByPrimaryKey(Integer userId);
    //查询需要导出的数据信息
    List<UserTb> findUsertbByIds(int[] uids);
    //动态新增用户
    int insertSelective(UserTb record);
    //批量删除用户
    public int deleteUsers(int[] uid);
    //通过主键删除用户
    int deleteByPrimaryKey(Integer userId);
    //通过主键查询用户以及用户角色
    public UserTb selectOneUsertbById(int userId);

}
