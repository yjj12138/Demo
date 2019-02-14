package com.dao;

import com.bean.UserTb;

import java.util.List;

public interface UserTbMapper {
    //通过主键删除用户
    int deleteByPrimaryKey(Integer userId);

    int insert(UserTb record);
    //动态新增用户
    int insertSelective(UserTb record);
    //通过主键查询用户
    UserTb selectByPrimaryKey(Integer userId);
    //根据主键修改用户
    int updateByPrimaryKeySelective(UserTb record);

    int updateByPrimaryKey(UserTb record);

    //登录
    UserTb login(String username);
    //查询需要导出的数据信息
    List<UserTb> findUsertbByIds(int[] uids);
    //批量删除用户
    public int deleteUsers(int[] uid);

    //通过主键查询用户以及用户角色
    public UserTb selectOneUsertbById(int userId);

    //根据用户id查询班主任id
    int findTeacherIdByUid(int userid);
}