package com.web;

import com.bean.Classes;
import com.bean.Department;
import com.bean.Major;
import com.github.pagehelper.PageInfo;
import com.service.ClassService;
import com.service.DepartmentService;
import com.service.MajorService;
import com.util.BookExcelPoi;
import com.util.ExcelPoi;
import com.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ClassController {
    /**
     * 分页步骤
     * 1.web-Controller层定义方法接收页面请求
     * (参数包括pageindex,需要设置默认值;有模糊查询的话,要添加用于接收模糊查询的参数)
     * 2.调用service层获取pageinfo对象
     * 3.service层定义获取pageinfo对象的方法
     * 4.service实现类实现service接口中的方法
     * (调用PageHelper的Startpage方法传入pageindex和pagesize
     * 如果有模糊查询,现将模糊查询的数据封装到map集合
     * 调用dao层方法,传入map,获取list集合
     * 创建pageinfo对象并将list作为参数
     * 用ModelMap存储pageinfo
     * 跳转页面
     * )
     *
     *
     *
     *
     */
    @Autowired
    private ClassService classService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private DepartmentService departmentService;
    //查询所有
    @RequestMapping("/Educational/class/list")
    public String getAll(ModelMap map, @RequestParam(value ="index",defaultValue ="1") int pageindex, String cname, String dname) {
        PageInfo<Classes> pageInfo = classService.getAll(pageindex, PageUtil.PAGESIZE, cname, dname);
        map.put("pageInfo",pageInfo);
        map.put("classnum",cname);
        map.put("classname",dname);
        return "Educational/class/list";
    }
    //查询学院
    @RequestMapping("/Educational/class/getDepart")
    public String getDepart(ModelMap map) {
        List<Department> list = classService.getDepart();
        map.put("list",list);
        return "/Educational/class/add";
    }
    //查询专业
    @RequestMapping("/getMajor")
    @ResponseBody
    public List getMajor(int departid) {
        List<Major> majors = classService.getMajor(departid);
        return majors;
    }
    //查询老师
    @RequestMapping("/getTeacher")
    @ResponseBody
    public List getTeacher(int majorid) {
        List teachers = classService.getTeacher(majorid);
        return teachers;
    }

    //添加班级
    @RequestMapping("/Educational/class/addclass")
    public String addClass(Classes classes,String teacher) {
        String[] strings = teacher.split("-");
        classes.setClassteacher(strings[1]);
        classes.setTeacherid(Integer.parseInt(strings[0]));
        classes.setClassstate("未审核");
        classes.setAuditcount(0);
        classService.insert(classes);
        return "redirect:/Educational/class/list";
    }

    //导出
    @RequestMapping("/daochu")
    public void daoChu(int[] cids) {
        List<Classes> list = classService.findByIds(cids);
            //导出数据
            ExcelPoi.heads = new String[]{"院系","班级编号","班级名称","班主任姓名","班级人数"};
            ExcelPoi.createOne();
            ExcelPoi.createOthers(list);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String s = simpleDateFormat.format(new Date());
            try {
                BookExcelPoi.export(new FileOutputStream("f:\\" + s +".xls"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }

    //班级详情
    @RequestMapping("/Educational/class/detail")
    public String detail(int classid,ModelMap map) {
        Classes classes = classService.findClassesAndDepartmentByCid(classid);
        map.put("classes",classes);
        return "/Educational/class/info";
    }

    //修改班级(准备)
    @RequestMapping("/Educational/class/updateready")
    public String updateReady(int classid,ModelMap map) {
        Classes classes = classService.findClassesAndDepartmentByCid(classid);
        List<Major> majors = majorService.getAll();
        List<Department> departments = departmentService.getAll();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String format1 = simpleDateFormat.format(classes.getClassbegin());
        String format2 = simpleDateFormat.format(classes.getClassend());
        map.put("classes",classes);
        map.put("majors",majors);
        map.put("departments",departments);
        map.put("format1",format1);
        map.put("format2",format2);
        return "/Educational/class/edit";
    }

    //修改班级(正式)
    @RequestMapping("/Educational/class/updateclass")
    public String updateClass(Classes classes) {
        classService.updateByPrimaryKeySelective(classes);
        return "redirect:/Educational/class/list";
    }

    //删除班级
    @RequestMapping("/Educational/class/deleteclass")
    public String deleteClass(int classid) {
        classService.deleteByPrimaryKey(classid);
        return "redirect:/Educational/class/list";
    }

    //审核通过
    @RequestMapping("/Educational/class/getthrough")
    public String getThrough(int classid) {
        classService.changeState(classid);
        return "redirect:/Educational/Auditing";
    }

    //审核驳回
    @RequestMapping("/Educational/class/notthrough")
    public String notThrough(int classid) {
        classService.notThrough(classid);
        return "redirect:/Educational/Auditing";
    }

    //班级详细
    @RequestMapping("/Educational/class/classdetailmsg")
    public String classDetailMsg(int classid,ModelMap map) {
        Classes classes = classService.findClassesAndDepartmentByCid(classid);
        map.put("classes",classes);
        return "/Educational/class/view";
    }
}
