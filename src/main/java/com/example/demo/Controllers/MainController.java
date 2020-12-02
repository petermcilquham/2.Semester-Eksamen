package com.example.demo.Controllers;

import com.example.demo.Services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@Controller
public class MainController {
    ObjectManager objectManager = new ObjectManager();
    ClearLists clearLists = new ClearLists();

    @GetMapping("/main")
    public String main(Model myProjects, Model sharedProjects, Model username) throws SQLException {
        clearLists.clearLists();
        objectManager.myProjectList = objectManager.pRep.getMyProjects(1);
        objectManager.sharedProjectList = objectManager.pRep.getSharedProjects(1);
        myProjects.addAttribute("myProjectList",objectManager.myProjectList);
        sharedProjects.addAttribute("sharedProjectList",objectManager.sharedProjectList);
        username.addAttribute("usernameFromCreatedBy",objectManager.uRep.getUserByID(objectManager.project.getCreatedBy()));
        return "main";
    }

    @PostMapping("/backtomain")
    public String backToMain() {
        clearLists.clearLists();
        return "redirect:/main";
    }
  
    @PostMapping("/createproject")
    public String createProject(WebRequest wr) throws SQLException {
        clearLists.clearLists();
        String projectName = wr.getParameter("projectName");

        //dette giver dagens dato i yyyy/mm/dd
        long millis = System.currentTimeMillis();
        java.sql.Date currentDay = new java.sql.Date(millis);

        //ændre til currentlogins id, når den er klar :)
        int createdBy = 1;

        objectManager.pRep.createProject(projectName, currentDay, createdBy);
        return "redirect:/main";
    }
}
