package com.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ProjectController {

    @GetMapping("project")
    public String projectPage() {
        return "project";
    }

    @PostMapping("/project")
    public String project(WebRequest wr) {
        String id = wr.getParameter("project");
        System.out.println(id);
        //kan ikke få id til at printe ud, den returnere bare Null
        return "redirect:/project";
    }
}
