package com.service.impl;

import com.bean.Leavebill;
import com.bean.UserTb;
import com.service.ActivityService;
import com.service.LeaveBillService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private LeaveBillService leaveBillService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @Override
    public int addProcess(InputStream inputStream, String processname) {
        repositoryService.createDeployment()
                .addZipInputStream(new ZipInputStream(inputStream))
                .name("demo1")
                .deploy();
        return 1;
    }

    @Override
    public List getDeploy() {
        return repositoryService.createDeploymentQuery().list();
    }

    @Override
    public List getProcessDef() {
        return repositoryService.createProcessDefinitionQuery().list();
    }

    @Override
    public int deleteDploy(String id) {
        repositoryService.deleteDeployment(id);
        return 1;
    }

    @Override
    public InputStream lookProcessPicture(String deploymentId, String resourceName) {
        return repositoryService.getResourceAsStream(deploymentId,resourceName);
    }

    @Override
    @Transactional
    public int commit(Leavebill leavebill, UserTb userTb) {
        //1.修改请假单
        leaveBillService.updateByPrimaryKeySelective(leavebill);
        //2.启动流程实例
        String processDefinitionKey = "Leavebill";
        String businessKey = processDefinitionKey + "." + leavebill.getId();
        Map map = new HashMap();
        map.put("uname",userTb.getUserName());
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, map);
        //完成学生任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(instance.getId())
                .singleResult();
        taskService.complete(task.getId());
        return 1;
    }

    @Override
    public List<Task> getTaskByUserid(int userid) {
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(userid + "")
                .list();
        return list;
    }

    @Override
    public Leavebill getLeavebillByTaskid(String taskid) {
        Task task = taskService.createTaskQuery()
                .taskId(taskid)
                .singleResult();
        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();
        String businessKey = instance.getBusinessKey();
        String id = businessKey.substring(businessKey.indexOf(".") + 1);
        Leavebill leavebill = leaveBillService.selectByPrimaryKey(Integer.parseInt(id));
        return leavebill;
    }

    @Override
    public int desolve(Leavebill leavebill, String result, String taskid, UserTb userTb,String pizhu) {
        //获得任务对象
        Task task = taskService.createTaskQuery()
                .taskId(taskid)
                .singleResult();
        //添加批注人姓名
        Authentication.setAuthenticatedUserId(userTb.getUserRealname());
        //获得流程实例id
        String processInstanceId = task.getProcessInstanceId();
        //添加批注信息
        taskService.addComment(taskid,processInstanceId,pizhu);


        Map map = new HashMap();
        map.put("mess",result);
        taskService.complete(taskid,map);
        if(result.equals("通过")) {
            ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
            if(instance != null) {
                leavebill.setState(2);
                leaveBillService.updateByPrimaryKeySelective(leavebill);
            }
        }else if(result.equals("驳回")) {
            leavebill.setState(3);
            leaveBillService.updateByPrimaryKeySelective(leavebill);
        }
        return 1;
    }

    @Override
    public List getOutLine(String taskid) {
        //1.通过节点id获得节点对象
        Task task = taskService.createTaskQuery()
                .taskId(taskid)
                .singleResult();
        //2.通过task对象获得流程定义id
        String processDefinitionId = task.getProcessDefinitionId();
        //3.通过流程定义id获取流程定义的实体对象
        ProcessDefinitionEntity processDefinition =
                (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        //4获取流程实例id
        String processInstanceId = task.getProcessInstanceId();
        //5.根据流程实例id获取流程实例对象
        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        //6.获得当前活动的节点id
        String activityId = instance.getActivityId();
        //7.根据流程实例获得所有的活动节点
        List<ActivityImpl> activities = processDefinition.getActivities();
        //8.遍历集合,找出所有活动节点中和当前活动id匹配的节点
        List list = new ArrayList();
        for (ActivityImpl activity : activities) {
            if(activity.getId().equals(activityId)) {
                //获得当前活动节点的所有输出线对象
                List<PvmTransition> outgoingTransitions =
                                                activity.getOutgoingTransitions();
                for (PvmTransition pvmTransition : outgoingTransitions) {
                    Object name = pvmTransition.getProperty("name");
                    list.add(name);
                }
            }
        }
        return list;
    }

    @Override
    public List getComments(String taskid) {
        //1.通过任务id获取流程实例id
        Task task = taskService.createTaskQuery()
                .taskId(taskid)
                .singleResult();
        String processInstanceId = task.getProcessInstanceId();
        //2.通过流程实例id查询历史任务
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();
        //3.得到每一个任务的批注信息
        List list1 = new ArrayList();
        for (HistoricTaskInstance historicTaskInstance : list) {
            List<Comment> comments = taskService.getTaskComments(historicTaskInstance.getId());
            list1.add(comments);
        }
        return list1;
    }
}
