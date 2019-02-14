package com.web;

import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class UsersController {
    @Autowired
    private UsersService service;
    @RequestMapping("/select")
    public String select(ModelMap map) {
        List list = service.getAll();
        map.put("ulist",list);
        return "success";
    }
}
