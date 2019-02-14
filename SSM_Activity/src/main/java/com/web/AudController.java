package com.web;

import com.bean.Classes;
import com.github.pagehelper.PageInfo;
import com.service.AudService;
import com.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AudController {
    @Autowired
    private AudService audService;
    @RequestMapping("/Educational/Auditing")
    public String getall(ModelMap map, @RequestParam(value ="index",defaultValue ="1") int pageindex, String cname, String dname) {
        PageInfo<Classes> pageInfo = audService.getAll(pageindex, PageUtil.PAGESIZE, cname, dname);
        map.put("pageInfo",pageInfo);
        map.put("cname",cname);
        map.put("dname",dname);
        return "Educational/Auditing";
    }
}
