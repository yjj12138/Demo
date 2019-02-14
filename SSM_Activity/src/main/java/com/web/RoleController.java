package com.web;

import com.bean.Menu;
import com.bean.Role;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import com.service.MiddleService;
import com.service.RoleService;
import com.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MiddleService middleService;
    @RequestMapping("/power/role/getroles")
    public String getAll(@RequestParam(value = "index",defaultValue = "1") int pageindex, ModelMap map) {
        PageInfo pageInfo = roleService.getAll(pageindex, PageUtil.PAGESIZE);
        map.put("pageInfo",pageInfo);
        return "/power/role/list";
    }

    //查询单个角色详情
    @RequestMapping("/power/role/getrolebyrid")
    public String getRoleByRid(int roleid,ModelMap map) {
        Role role = roleService.getRole(roleid);
        map.put("role",role);
        return "power/role/info";
    }

    //修改单个角色信息(后台准备数据)
    @RequestMapping("/power/role/selectbyrid")
    public String updateRoleByRid(Role role,ModelMap map) {
        //查询角色信息和其拥有的菜单信息
        Role role1 = roleService.selectByPrimaryKey(role.getRoleid());
        map.put("role1",role1);
        //获取所有的菜单信息(分级)
        List<Menu> allMenus = menuService.getAllMenus();
        map.put("allMenus",allMenus);
        return "/power/role/edit";
    }

    //修改角色信息(正式修改)
    @RequestMapping("/power/role/updaterole")
    public String updateRole(int[] menuid,Role role) {
        roleService.updateRole(menuid,role);
        return "redirect:/power/role/getroles";
    }

    //修改角色状态
    @RequestMapping("/power/role/updatestate")
    public String updateState(Role role) {
        role.setRolestate(role.getRolestate() == PageUtil.ABLE?PageUtil.DISABLE:PageUtil.ABLE);
        roleService.updateByPrimaryKeySelective(role);
        return "redirect:/power/role/getroles";
    }

    //删除单个角色信息
    @RequestMapping("/power/role/deleterolebyrid")
    public void deleteRoleByRid(int roleid, HttpServletResponse  response){
        int i = roleService.selectusertbbyrid(roleid);
        if (i > 0) {
            try {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("<script>alert('该角色正被其他用户使用,不能删除!');location.href='/power/role/getroles'</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            middleService.deleteRoleAndMenubyRid(roleid);
            roleService.deleteByPrimaryKey(roleid);
            try {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("<script>alert('删除角色成功');location.href='/power/role/getroles'</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
