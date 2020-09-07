package com.example.demo.springDemo.bean.scope.web;

import com.example.demo.springDemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {
    @Autowired
    private User user;
    @RequestMapping("index.html")
    public String index(Model model){
//        model.addAttribute("user",user);
        model.addAttribute("userObject",user);
        return "index";
    }
}
