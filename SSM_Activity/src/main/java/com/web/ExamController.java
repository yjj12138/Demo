package com.web;

import com.bean.Department;
import com.bean.Exam;
import com.bean.Major;
import com.github.pagehelper.PageInfo;
import com.service.ExamService;
import com.service.StudentService;
import com.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ExamController {
    @Autowired
    private ExamService service;
    @Autowired
    private StudentService studentService;
    @RequestMapping("/Educational/exam/exam")
    public String getAll(ModelMap map,String ename,@RequestParam(value = "index",defaultValue = "1") int pageindex) {
        PageInfo<Exam> pageInfo = service.getAll(pageindex, PageUtil.PAGESIZE, ename);
        map.put("pageInfo",pageInfo);
        map.put("ename",ename);
        return "Educational/exam/exam";
    }

    //查询学院
    @RequestMapping("/Educational/exam/getDepart")
    public String getDepart(ModelMap map) {
        List<Department> list = service.getDepart();
        map.put("list",list);
        return "/Educational/exam/add";
    }

    //新增考试
    @RequestMapping("/Educational/exam/addExam")
    public String addExam(Exam exam) {
        service.insert(exam);
        return "redirect:/Educational/exam/exam";
    }

    //修改考试(准备工作)
    @RequestMapping("/Educational/exam/updateexamready")
    public String updateExamReady(int examid,ModelMap map) {
        List<Department> list = service.getDepart();
        Exam exam = service.selectByPrimaryKey(examid);
        map.put("list",list);
        map.put("exam",exam);
        return "/Educational/exam/update";
    }

    //修改考试
    @RequestMapping("/Educational/exam/updateExam")
    public String updateExam(Exam exam) {
        service.updateByPrimaryKeySelective(exam);
        return "redirect:/Educational/exam/exam";
    }

    //考试详细
    @RequestMapping("/Educational/exam/examdetail")
    public String examDetail(int examid,ModelMap map) {
        Exam exam = service.getExamAndClasses(examid);
        map.put("exam",exam);
        return "/Educational/exam/view";
    }

    //删除考试
    @RequestMapping("/Educational/exam/deleteexam")
    public String deleteExam(int examid) {
        service.deleteByPrimaryKey(examid);
        return "redirect:/Educational/exam/exam";
    }

    //考试成绩
    @RequestMapping("/Educational/exam/getStudentScore")
    public String getStudentScore(int examid,ModelMap map,@RequestParam(value = "index",defaultValue = "1") int pageindex,@RequestParam(value = "size",defaultValue = "5") int pagesize,String stuname) {
        PageInfo pageInfo = studentService.getExamStudentAndScoresByEid(examid, pageindex, pagesize, stuname);
        Exam exam = service.selectByPrimaryKey(examid);
        map.put("pageInfo",pageInfo);
        map.put("exam",exam);
        map.put("stuname",stuname);
        map.put("examid",examid);
        return "/Educational/exam/list";
    }

    //组织补考(页面展示)
    @RequestMapping("/Educational/exam/readdexamready")
    public String reAddExamReady(int examid,ModelMap map,String classname) {
        Exam exam = service.getExamAndClasses(examid);
        List<Department> list = service.getDepart();
        List<Major> major = service.getMajor();
        map.put("major",major);
        map.put("list",list);
        map.put("exam",exam);
        map.put("classname",classname);
        return "/Educational/exam/reAdd";
    }

    //组织补考
    @RequestMapping("/Educational/exam/readdexam")
    public String readdExam(Exam exam) {
        service.insertSelective(exam);
        return "redirect:/Educational/exam/exam";
    }
}
