package com.dao;

import com.bean.Middle;

public interface MiddleMapper {
    int deleteByPrimaryKey(Integer middleid);

    int insert(Middle record);

    int insertSelective(Middle record);

    Middle selectByPrimaryKey(Integer middleid);

    int updateByPrimaryKeySelective(Middle record);

    int updateByPrimaryKey(Middle record);

    //通过角色id删除middle中角色和菜单对应的关系
    int deleteRoleAndMenubyRid(int roleid);
}