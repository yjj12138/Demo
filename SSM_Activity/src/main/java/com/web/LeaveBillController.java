package com.web;

import com.bean.Leavebill;
import com.bean.UserTb;
import com.service.LeaveBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LeaveBillController {

    @Autowired
    private LeaveBillService leaveBillService;

    //添加请假单
    @RequestMapping("/qingjia/add")
    public String addQingJia(Leavebill leavebill) {
        leaveBillService.insertSelective(leavebill);
        return "redirect:/qingjia/getlist";
    }

    //查询请假单
    @RequestMapping("/qingjia/getlist")
    public String getList(HttpSession session, ModelMap map) {
        UserTb usertb = (UserTb) session.getAttribute("usertb");
        List<Leavebill> leavebills = leaveBillService.getList(usertb.getUserId());
        map.put("leavebills",leavebills);
        return "/qingjia/list";
    }
}
