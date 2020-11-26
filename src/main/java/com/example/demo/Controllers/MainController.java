package com.example.demo.Controllers;

import com.example.demo.Models.Project;
import com.example.demo.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    UserRepository ur = new UserRepository();
    List<Project> projectList = new ArrayList<>();


    @GetMapping("/main")
    public String main(Model m) throws SQLException {
        projectList = ur.getUsersProjects(1);
        System.out.println("number of projects: " + projectList.size());
        m.addAttribute("projectList",projectList);
        return "main";
    }

    @PostMapping("/backtomain")
    public String backToMain() {
        projectList.clear();
        return "redirect:/main";
    }

    @PostMapping("/project")
    public String project() {
        return "project";
    }

    //@PostMapping("/projects")
    //controller klik på/vis projekt
}
