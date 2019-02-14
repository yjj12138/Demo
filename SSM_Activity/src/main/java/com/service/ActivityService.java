package com.service;

import com.bean.Leavebill;
import com.bean.UserTb;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;

public interface ActivityService {
    //新增部署文件
    int addProcess(InputStream inputStream,String processname);
    //查询部署文件
    List getDeploy();
    //查询流程定义
    List getProcessDef();
    //删除部署信息
    int deleteDploy(String id);
    //查看流程图
    InputStream lookProcessPicture(String deploymentId,String resourceName);
    //提交请假单
    int commit(Leavebill leavebill, UserTb userTb);
    //查询个人任务
    List<Task> getTaskByUserid(int userid);
    //根据任务id查询请假单数据
    Leavebill getLeavebillByTaskid(String taskid);
    //上级审批处理
    int desolve(Leavebill leavebill,String result,String taskid,UserTb userTb,String pizhu);
    //获得任务节点的所有输出线信息
    List getOutLine(String taskid);
    //根据任务id获取历史批注信息
    List getComments(String taskid);
}
