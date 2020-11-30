package com.example.demo.Controllers;

import com.example.demo.Models.Project;
import com.example.demo.Models.Task;
import com.example.demo.Repositories.ProjectRepository;
import com.example.demo.Repositories.TaskRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.ListManager;
import com.example.demo.Services.ModelManager;
import com.example.demo.Services.RepositoryManager;
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
    RepositoryManager repositoryManager = new RepositoryManager();
    ModelManager modelManager = new ModelManager();
    ListManager listManager = new ListManager();
//    List<Project> myProjectList = new ArrayList<>();
//    List<Project> sharedProjectList = new ArrayList<>();
//    Project p = new Project(0,"",null,0);
//    TaskRepository tRep = new TaskRepository();
//    List<Project> singleProjectList = new ArrayList<>();
//    List<Task> listOfTasks = new ArrayList<>();
//    ProjectRepository pRep = new ProjectRepository();
//    UserRepository uRep = new UserRepository();

//    public void clearLists(){
//        //clear array lists
//        myProjectList.clear();
//        sharedProjectList.clear();
//    }

    @GetMapping("/main")
    public String main(Model myProjects, Model sharedProjects, Model username) throws SQLException {
        listManager.clearLists();
        listManager.myProjectList = repositoryManager.pRep.getMyProjects(2);
        listManager.sharedProjectList = repositoryManager.pRep.getSharedProjects(2);
        myProjects.addAttribute("myProjectList",listManager.myProjectList);
        sharedProjects.addAttribute("sharedProjectList",listManager.sharedProjectList);
        username.addAttribute("usernameFromCreatedBy",repositoryManager.uRep.getUserByID(modelManager.project.getCreatedBy()));
        return "main";
    }

    @PostMapping("/backtomain")
    public String backToMain() {
        listManager.clearLists();
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

        repositoryManager.pRep.createProject(projectName, currentDay, createdBy);
        return "redirect:/main";
    }

}
