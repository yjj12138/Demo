package com.web;

import cn.com.webxml.MobileCodeWSSoap;
import com.bean.Role;
import com.bean.UserTb;
import com.service.RoleService;
import com.service.UserTbService;
import com.util.Message;
import com.util.UserExcelPoi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UsertbController {
    @Autowired
    private UserTbService userTbService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MobileCodeWSSoap soap;
    //登录
    @RequestMapping("/login")
    public void login(UserTb userTb, HttpServletResponse response, HttpSession session) {
        UserTb tb = userTbService.login(userTb);
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            if (tb != null) {
                //修改登录次数
                tb.setLogincount(tb.getLogincount() + 1);
                userTbService.updateByPrimaryKeySelective(tb);
                session.setAttribute("usertb",tb);
                session.setAttribute("logintime",new Date());
                writer.write("<script>alert('登录成功');location.href='index.jsp'</script>");
            }else {
                writer.write("<script>alert('用户名或密码不正确,请重新登录');location.href='login.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //退出登录
    @RequestMapping("/loginout")
    public String loginout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.jsp";
    }

    //更新用户信息
    @RequestMapping("/updateuser")
    public void updateuser(UserTb userTb,HttpSession session,HttpServletResponse response) {
        int i = userTbService.updateByPrimaryKeySelective(userTb);
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            if(i>0){
                UserTb userTb1 = userTbService.selectByPrimaryKey(userTb.getUserId());
                session.setAttribute("usertb",userTb1);
                writer.write("<script>alert('更新成功');top.location.href='/index.jsp'</script>");
            }else {
                writer.write("<script>alert('更新失败');location.href='/user/MyUser.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //修改密码
    @RequestMapping("/updatepass")
    public void updatepass(UserTb userTb,HttpServletResponse response) {
        int i = userTbService.updateByPrimaryKeySelective(userTb);
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            if(i>0){
                writer.write("<script>alert('修改密码成功');top.location.href='/loginout'</script>");
            }else {
                writer.write("<script>alert('修改密码失败');location.href='/user/password.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //导出
    @RequestMapping("/daochuuser")
    public void daoChuUser(int[] uids) {
        List<UserTb> list = userTbService.findUsertbByIds(uids);
        //导出数据
        UserExcelPoi.heads = new String[]{"序号","账号","姓名","角色"};
        UserExcelPoi.createOne();
        UserExcelPoi.createOthers(list);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String s = simpleDateFormat.format(new Date());
        try {
            UserExcelPoi.export(new FileOutputStream("f:\\" +s+".xls"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //查询用户信息和角色信息
    @RequestMapping("/power/user/selectusertb")
    public String selectUserTb(int userId, ModelMap map) {
        UserTb userTb = userTbService.selectByPrimaryKey(userId);
        List<Role> roles = roleService.getAll();
        map.put("usertb",userTb);
        map.put("roles",roles);
        return "/power/user/edit";
    }

    //修改用户信息
    @RequestMapping("/power/user/updateUsertb")
    public String updateUsertb(UserTb userTb) {
        userTbService.updateByPrimaryKeySelective(userTb);
        return "redirect:/power/user/getusers";
    }

    //通过主键删除用户
    @RequestMapping("/power/user/deleteuser")
    public String deleteUsertb(int userId) {
        userTbService.deleteByPrimaryKey(userId);
        return "redirect:/power/user/getusers";
    }

    //用户详情
    @RequestMapping("/power/user/selectoneusertb")
    public String selectOneUsertb(int userId,ModelMap map) {
        UserTb userTb = userTbService.selectOneUsertbById(userId);
        map.put("userTb",userTb);
        return "/power/user/info";
    }

    //查询归属地
    @RequestMapping("getphoneaddress")
    @ResponseBody
    public Message getPhoneAddress(String phone) {
        String info = soap.getMobileCodeInfo("phone", "");
        Message message = new Message();
        message.setStr(info);
        return message;
    }

}
