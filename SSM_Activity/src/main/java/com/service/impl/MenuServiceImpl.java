package com.service.impl;

import com.bean.Menu;
import com.bean.Role;
import com.dao.MenuMapper;
import com.dao.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public PageInfo getAll(int pageindex, int pagesize) {
        PageHelper.startPage(pageindex, pagesize);
        List list = menuMapper.getAll();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo getmenus(int pageindex, int pagesize) {
        PageHelper.startPage(pageindex, pagesize);
        List list = menuMapper.getmenus();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Autowired
    private DataSourceTransactionManager tx;
    //批量删除菜单
    @Override
    public int deleteMenus(String[] mid) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = tx.getTransaction(definition);
        try {
            //1.取出所有的菜单id
            List ids = new ArrayList();
            for (String s : mid) {
                String id = s.substring(0,s.indexOf("-"));
                ids.add(Integer.parseInt(id));
            }
            //判断这些菜单是否正被角色使用
            int count = menuMapper.selectCountByMid(ids);
            if (count > 0) {
                return 0;
            }else {
                //判断给定菜单的一二级关系(分类存储)
                List first = new ArrayList();
                List second = new ArrayList();
                for (String s : mid) {
                    String begin = s.substring(0,s.indexOf("-"));
                    String end = s.substring(s.indexOf("-") + 1);
                    if(Integer.parseInt(end)==-1) {
                        first.add(begin);
                    }else {
                        second.add(begin);
                    }
                }

                //分三种情况讨论1.全是一级菜单2.全是二级菜单3.一二级菜单都有
                if(first.size() == mid.length) {
                    //全是一级菜单,继续查询其下有无关联的二级菜单
                    int count1 = menuMapper.selectSecondCountByMids(first);
                    if (count1 > 0) {
                        return 0;
                    }else {
                        menuMapper.deleteMenus(first);
                        tx.commit(status);
                        return 1;
                    }
                }else if(second.size() == mid.length) {
                    //全是二级菜单,可以直接删除
                    menuMapper.deleteMenus(second);
                    tx.commit(status);
                    return 1;
                }else {
                    //一二级菜单都有,先删除给定的二级菜单
                    menuMapper.deleteMenus(second);
                    //再查询一级菜单下是否还有二级菜单
                    int count2 = menuMapper.selectSecondCountByMids(first);
                    if(count2 > 0) {
                        throw new Exception("");
                    }else {
                        menuMapper.deleteMenus(first);
                    }
                }
            }
            tx.commit(status);
            return 1;
        } catch (Exception e) {
            tx.rollback(status);
        }
        return 0;
    }

    @Override
    public List<Menu> findMenusByMids(int[] mid) {
        return menuMapper.findMenusByMids(mid);
    }

    @Override
    public List getAllMenus() {
        List<Menu> menus = menuMapper.getmenus();
        //分一二级菜单
        List oneList = new ArrayList();//用于存储一级菜单信息
        for (Menu menu : menus) {
            if(menu.getUpmenuid() == -1) {
                List second = new ArrayList();//用于存储一级菜单对应的二级菜单信息
                for (Menu menu1 : menus) {
                    if(menu1.getUpmenuid() == menu.getMenuid()) {
                        second.add(menu1);
                    }
                }
                //建立一级菜单对象和二级菜单集合的关系
                menu.setSecond(second);
                oneList.add(menu);
            }
        }
        return oneList;
    }

    @Override
    @Transactional
    public int addRoles(Role role, int[] menuid) {
        roleMapper.insertSelective(role);
        Map map = new HashMap();
        map.put("menuid",menuid);
        map.put("roleid",role.getRoleid());
        int i = menuMapper.addMiddle(map);
        return i;
    }

    @Override
    public List getAllFirstMenu() {
        return menuMapper.getAllFirstMenu();
    }
    //动态新增
    @Override
    public int insertSelective(Menu record) {
        return menuMapper.insertSelective(record);
    }

    @Override
    public Menu selectByPrimaryKey(Integer menuid) {
        return menuMapper.selectByPrimaryKey(menuid);
    }

    @Override
    @Transactional
    public int deletemenubymenuid(int menuid, int upmenuid) {
        int i = menuMapper.selectonecountbymid(menuid);
        if(i > 0) {
            return 0;
        }else {
            if(upmenuid == -1) {
                int i1 = menuMapper.selectOneSecondCountByMid(menuid);
                if (i1 > 0) {
                    return 0;
                }else {
                    menuMapper.deleteByPrimaryKey(menuid);
                    return 1;
                }
            }else {
                menuMapper.deleteByPrimaryKey(menuid);
                return 1;
            }
        }
    }

    @Override
    @Transactional
    public int updateMenuByPrimaryKeySelective(Menu record,int oldupmenuid) {
        if(oldupmenuid != -1) {
            menuMapper.updateByPrimaryKeySelective(record);
        }else {
            if(record.getMenuid() == record.getUpmenuid()) {
                return 0;
            }else {
                int i = menuMapper.selectOneSecondCountByMid(record.getMenuid());
                if(i > 0) {
                    return 0;
                }else {
                    int i1 = menuMapper.updateByPrimaryKeySelective(record);
                    return i1;
                }
            }
        }
        return 0;
    }


}
