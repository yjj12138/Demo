package com.service;

public interface MiddleService {


    //通过角色id删除middle中角色和菜单对应的关系
    int deleteRoleAndMenubyRid(int roleid);
}
