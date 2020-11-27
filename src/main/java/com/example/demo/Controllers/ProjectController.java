package com.example.demo.Controllers;

import com.example.demo.Models.Project;
import com.example.demo.Models.Task;
import com.example.demo.Repositories.ProjectRepository;
import com.example.demo.Repositories.TaskRepository;
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
public class ProjectController {
    UserRepository uRep = new UserRepository();
    ProjectRepository pRep = new ProjectRepository();
    TaskRepository tRep = new TaskRepository();
    List<Project> singleProjectList = new ArrayList<>();
    List<Task> listOfTasks = new ArrayList<>();

    @GetMapping("/project")
    public String projectPage(Model m, Model m2) throws SQLException {
        m2.addAttribute("taskList",listOfTasks);
        m.addAttribute("singleProject",singleProjectList);
        return "project";
    }

    @PostMapping("/project/delete")
    public String deleteProject(WebRequest wr) throws SQLException {
        String tempID = wr.getParameter("deleteProject");
        int projectID = Integer.parseInt(tempID);
        pRep.deleteProject(projectID);
        return "redirect:/main";
    }

    @PostMapping("/getproject")
    public String project(WebRequest wr) throws SQLException{
        String tempID = wr.getParameter("projectID");
        System.out.println(tempID);
        int projectID = Integer.parseInt(tempID);

        singleProjectList.clear();
        listOfTasks.clear();
        singleProjectList = pRep.getSingleProject(projectID);
        listOfTasks = tRep.getTaskList(projectID);

        return "redirect:/project";
    }


}
