package com.example.demo.Controllers;

import com.example.demo.Models.Project;
import com.example.demo.Repositories.ProjectRepository;
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
    ProjectRepository pRep = new ProjectRepository();
    UserRepository uRep = new UserRepository();
    Project p = new Project(0,"",null,0);
    List<Project> myProjectList = new ArrayList<>();
    List<Project> sharedProjectList = new ArrayList<>();

    public void clearLists(){
        //clear array lists
        myProjectList.clear();
        sharedProjectList.clear();
    }

    @GetMapping("/main")
    public String main(Model myProjects, Model sharedProjects, Model username) throws SQLException {
        clearLists();
        myProjectList = pRep.getMyProjects(2);
        sharedProjectList = pRep.getSharedProjects(2);
        myProjects.addAttribute("myProjectList",myProjectList);
        sharedProjects.addAttribute("sharedProjectList",sharedProjectList);
        username.addAttribute("usernameFromCreatedBy",uRep.getUserByID(p.getCreatedBy()));
        return "main";
    }

    @PostMapping("/backtomain")
    public String backToMain() {
        clearLists();
        return "redirect:/main";
    }

    @PostMapping("/project")
    public String project() {
        return "project";
    }

    //@PostMapping("/projects")
    //controller klik på/vis projekt
}
