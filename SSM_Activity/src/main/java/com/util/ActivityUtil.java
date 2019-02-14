package com.util;

import com.bean.UserTb;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class ActivityUtil implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        //1.得到当前session中用户id
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserTb usertb = (UserTb) session.getAttribute("usertb");
        if(usertb.getRole().getRolename().equals("学生")) {
            delegateTask.setAssignee(usertb.getTeacherid() + "");
        }else if(usertb.getRole().getRolename().equals("班主任")) {
            delegateTask.setAssignee(usertb.getManagerid() + "");
        }
    }
}
