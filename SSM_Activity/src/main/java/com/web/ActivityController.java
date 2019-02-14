package com.web;

import com.bean.Leavebill;
import com.bean.UserTb;
import com.service.ActivityService;
import com.service.UserTbService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserTbService userTbService;
    //新增部署文件
    @RequestMapping("/bushu/addprocess")
    public String addProcess(String processname, MultipartFile processfile) {
        try {
            activityService.addProcess(processfile.getInputStream(),processname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/bushu/getlist";
    }
    //查询部署信息
    @RequestMapping("/bushu/getlist")
    public String getBushuList(ModelMap map) {
        //1.查询部署信息
        List<Deployment> deploy = activityService.getDeploy();
        //2.查询流程定义信息
        List<ProcessDefinition> processDef = activityService.getProcessDef();
        map.put("deploy",deploy);
        map.put("processDef",processDef);
        return "/bushu/list";
    }

    //删除部署信息
    @RequestMapping("/bushu/deletedeploy")
    public String deleteDeploy(String id) {
        activityService.deleteDploy(id);
        return "redirect:/bushu/getlist";
    }

    //查看流程图
    @RequestMapping("/bushu/lookprocesspicture")
    public void lookProcessPicture(String deploymentId, String resourceName, HttpServletResponse response) {
        InputStream inputStream = activityService.lookProcessPicture(deploymentId, resourceName);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            int b = -1;
            while((b= inputStream.read()) != -1) {
                outputStream.write(b);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //提交请假单
    @RequestMapping("/qingjia/commit")
    public String commit(Leavebill leavebill, HttpSession session) {
        leavebill.setState(1);
        UserTb usertb = (UserTb) session.getAttribute("usertb");
        activityService.commit(leavebill,usertb);
        return "redirect:/qingjia/getlist";
    }

    //查询个人任务
    @RequestMapping("/renwu/getlist")
    public String getRenWuList(HttpSession session,ModelMap map) {
        UserTb usertb = (UserTb) session.getAttribute("usertb");
        List<Task> tasks = activityService.getTaskByUserid(usertb.getUserId());
        map.put("tasks",tasks);
        return "/renwu/list";
    }

    //办理任务
    //根据任务id查询请假单数据
    @RequestMapping("/renwu/desolve")
    public String desolve(String taskid,ModelMap map) {
        Leavebill leavebill = activityService.getLeavebillByTaskid(taskid);
        map.put("leavebill",leavebill);
        UserTb userTb = userTbService.selectByPrimaryKey(leavebill.getUserid());
        //获得任务节点的所有输出线信息
        List outLines = activityService.getOutLine(taskid);
        //根据任务id得到历史批注信息
        List comments = activityService.getComments(taskid);
        map.put("ut",userTb);
        map.put("taskid",taskid);
        map.put("name",outLines);
        map.put("comments",comments);
        return "/renwu/banli";
    }

    //上级审批
    @RequestMapping("/renwu/shenpi")
    public String shenPi(Leavebill leavebill,String taskid,String result,HttpSession session,String pizhu) {
        UserTb usertb = (UserTb) session.getAttribute("usertb");
        activityService.desolve(leavebill,result,taskid,usertb,pizhu);
        return "redirect:/renwu/getlist";
    }

}
