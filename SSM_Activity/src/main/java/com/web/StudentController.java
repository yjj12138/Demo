package com.web;

import com.bean.Classes;
import com.bean.Department;
import com.bean.Major;
import com.bean.Student;
import com.github.pagehelper.PageInfo;
import com.service.ClassService;
import com.service.DepartmentService;
import com.service.MajorService;
import com.service.StudentService;
import com.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService service;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ClassService classService;
    @RequestMapping("/Educational/student/list")
    public String getAll(ModelMap map, @RequestParam(value = "index",defaultValue = "1") int pageindex, String sno, String sname, String sgender) {
        System.out.println("======");
        PageInfo<Student> pageInfo = service.getAll(pageindex, PageUtil.PAGESIZE, sname, sno, sgender);
        map.put("pageInfo",pageInfo);
        map.put("sname",sname);
        map.put("sno",sno);
        map.put("sgender",sgender);
        return "Educational/student/list";
    }
    //查询学院
    @RequestMapping("/Educational/student/getDepart")
    public String getDepart(ModelMap map) {
        List<Department> list = service.getDepart();
        map.put("list",list);
        return "/Educational/student/add";
    }
    //查询班级
    @RequestMapping("/getClasses")
    @ResponseBody
    public List getClasses(int majorid) {
        List classes = service.getClasses(majorid);
        return classes;
    }

    //添加学生
    @RequestMapping("/Educational/student/addStudent")
    public String addStudent(Student student) {
        student.setRegdate(new Date());
        student.setStustate("正常");
        service.insert(student);
        return "redirect:/Educational/student/list";
    }

    //修改学生信息(准备工作)
    @RequestMapping("/Educational/student/updatestudentready")
    public String updateStudentReady(int studentid,ModelMap map) {
        Student stu = service.selectByPrimaryKey(studentid);
        List<Department> dlist = departmentService.getAll();
        List<Major> mlist = majorService.getAll();
        List<Classes> classes = classService.getAllClasses();
        map.put("stu",stu);
        map.put("dlist",dlist);
        map.put("mlist",mlist);
        map.put("classes",classes);
        return "/Educational/student/edit";
    }

    //修改学生
    @RequestMapping("/Educational/student/updatestudent")
    public String updateStudent(Student student) {
        service.updateByPrimaryKeySelective(student);
        return "redirect:/Educational/student/list";
    }

    //退学
    @RequestMapping("/Educational/student/exitstudent")
    public String exitStudent(Student student) {
        student.setStustate("退学");
        service.updateByPrimaryKeySelective(student);
        return "redirect:/Educational/student/list";
    }

    //入学
    @RequestMapping("/Educational/student/inputstudent")
    public String inputstudent(Student student) {
        student.setStustate("入学");
        service.updateByPrimaryKeySelective(student);
        return "redirect:/Educational/student/list";
    }

    //学生详情
    @RequestMapping("/Educational/student/detailstudent")
    public String detailStudent(int studentid,ModelMap map) {
        Student student = service.getStuAndMajor(studentid);
        map.put("stu",student);
        return "/Educational/student/view";
    }
}
