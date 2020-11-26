package com.example.demo.Controllers;

import com.example.demo.Models.Project;
import com.example.demo.Repositories.ProjectRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    UserRepository ur = new UserRepository();
    ProjectRepository pr = new ProjectRepository();
    List<Project> projectList = new ArrayList<>();

    @GetMapping("/main")
    public String main(Model m) throws SQLException {
        projectList.clear();
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

    @PostMapping("/createproject")
    public String createProject(WebRequest wr) throws SQLException {
        String projectName = wr.getParameter("projectName");

        //dette giver dagens dato i yyyy/mm/dd
        long millis = System.currentTimeMillis();
        java.sql.Date currentDay = new java.sql.Date(millis);

        //ændre til currentlogins id, når den er klar :)
        int createdBy = 1;

        pr.createProject(projectName, currentDay, createdBy);
        return "redirect:/main";


    }
}
