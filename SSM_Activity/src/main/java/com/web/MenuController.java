package com.web;

import com.bean.Menu;
import com.bean.Role;
import com.bean.UserTb;
import com.github.pagehelper.PageInfo;
import com.service.BooksService;
import com.service.MenuService;
import com.service.RoleService;
import com.service.UserTbService;
import com.util.MenuExcelPoi;
import com.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MenuController {
    /**
     * web  -- public String 方法名(
     *      @RequestParam(value="",defaultValue="")
     *      int pageindex,模糊查询的参数){
     *         //调取servcie层代码
     *        }
     * service  --
     * public PageInfo  方法名(int pageindex,int pagesize,模糊查询参数){
     *     //1.指定分页的参数
     *     PageHelper.startPage(pageindex,pagesize);
     *     //2.封装模糊查数据到map集合
     *     Map map=new HashMap();
     *     3.调取dao层方法
     *     List list= 方法调用(map);
     *     //4.创建PageInfo 对象，并将list作为参数传入
     * }
     * dao--
     *    public List 方法名(Map map);
     *
     * Mapper.xml文件
     *    定义sql语句
     */
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserTbService userTbService;
    @Autowired
    private BooksService booksService;
    @RequestMapping("/power/user/getusers")
    public String getAll(ModelMap map,@RequestParam(value = "index",defaultValue = "1") int pageindex) {
        PageInfo pageInfo = menuService.getAll(pageindex,PageUtil.PAGESIZE);
        map.put("pageInfo",pageInfo);
        return "/power/user/list";
    }
    //获取菜单信息
    @RequestMapping("/power/menu/getmenus")
    public String getmenus(ModelMap map,@RequestParam(value = "index",defaultValue = "1") int pageindex,
    @RequestParam(value = "size",defaultValue = "5") int size) {
        PageInfo pageInfo = menuService.getmenus(pageindex, size);
        map.put("pageInfo",pageInfo);
        return "/power/menu/list";
    }

    //获取用户角色信息
    @RequestMapping("/power/user/getroles")
    public String getRoles(ModelMap map) {
        List list = roleService.getAll();
        map.put("rlist",list);
        return "/power/user/add";
    }

    //新增角色
    @RequestMapping("/power/role/addroles")
    public String addRoles(int[] menuid, Role role) {
        menuService.addRoles(role,menuid);
        return "redirect:/power/role/getroles";
    }

    //新增用户
    @RequestMapping("/power/user/adduser")
    public String addUser(UserTb userTb) {
        userTbService.insertSelective(userTb);
        return "redirect:/power/user/getusers";
    }
    //批量删除用户
    @RequestMapping("/power/user/deleteusers")
    public String deleteUsers(int[] uid) {
        userTbService.deleteUsers(uid);
        return "redirect:/power/user/getusers";
    }
    //批量删除菜单
    @RequestMapping("power/menu/deletemenus")
    public void deleteMenus(String[] mid,HttpServletResponse response) {
        int i = menuService.deleteMenus(mid);
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            if (i > 0) {
                writer.write("<script>alert('删除成功');location.href='/power/menu/getmenus';</script>");
            }else {
                writer.write("<script>alert('删除失败');location.href='/power/menu/getmenus';</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //批量删除书籍资料
    @RequestMapping("/deletebook")
    public String deleteBooks(int[] bid) {
        booksService.deleteBooks(bid);
        return "redirect:/book/getbooks";
    }

    //导出Excel
    @RequestMapping("/power/menu/daochumenus")
    public void daochuMenu(int[] mid, HttpServletResponse response) {
        List<Menu> list = menuService.findMenusByMids(mid);
        MenuExcelPoi.heads = new String[]{"序号","菜单名称","URL","状态"};
        MenuExcelPoi.createOne();
        MenuExcelPoi.createOthers(list);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String s = simpleDateFormat.format(new Date());
        response.setContentType("application/x-download");
        String filename = s + ".xls";
        response.addHeader("Content-Disposition","attachement:filename=" + filename);
        try {
            MenuExcelPoi.export(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //获取菜单信息
    @RequestMapping("/power/role/getmenus")
    public String getMenus(ModelMap map) {
        List list = menuService.getAllMenus();
        map.put("mlist",list);
        return "/power/role/add";
    }

    //查询一二级菜单名称
    @RequestMapping("/power/menu/selectmenuname")
    public String selectMenuname(ModelMap map) {
        List list = menuService.getAllFirstMenu();
        map.put("mlist",list);
        return "/power/menu/add";
    }

    //新增菜单
    @RequestMapping("/power/menu/addmenu")
    public String addMenu(Menu menu) {
        menuService.insertSelective(menu);
        return "redirect:/power/menu/getmenus";
    }

    //菜单详情
    @RequestMapping("/power/menu/details")
    public String details(int menuid,ModelMap map,int upmenuid) {
        Menu menu = menuService.selectByPrimaryKey(menuid);
        Menu menu1 = menuService.selectByPrimaryKey(upmenuid);
        map.put("menu",menu);
        map.put("upmenu",menu1);
        return "/power/menu/info";
    }

    //删除单个菜单
    @RequestMapping("/power/menu/deletemenubymenuid")
    public void deletemenubymenuid(int menuid,int upmenuid,HttpServletResponse response) {
        int i = menuService.deletemenubymenuid(menuid, upmenuid);
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            if (i > 0) {
                writer.write("<script>alert('删除成功');location.href='/power/menu/getmenus';</script>");
            }else {
                writer.write("<script>alert('删除失败');location.href='/power/menu/getmenus';</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //修改菜单(修改页面展示)
    @RequestMapping("/power/menu/updatemenuready")
    public String updateMenuReady(int menuid, ModelMap map) {
        Menu menu = menuService.selectByPrimaryKey(menuid);
        List list = menuService.getAllFirstMenu();
        map.put("list",list);
        map.put("menu",menu);
        return "power/menu/edit";
    }
    //修改菜单(正式修改)
    @RequestMapping("/power/menu/updatemenu")
    public String updateMenu(Menu menu,int oldupmenuid) {
        menuService.updateMenuByPrimaryKeySelective(menu,oldupmenuid);
        return "redirect:/power/menu/getmenus";
    }
}
