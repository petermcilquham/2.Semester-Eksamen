package com.example.demo.Controllers;

import com.example.demo.Models.Project;
import com.example.demo.Repositories.ProjectRepository;
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
    //UserRepository uRep = new UserRepository();
    ProjectRepository pRep = new ProjectRepository();
    List<Project> projectList = new ArrayList<>();

    @GetMapping("/project")
    public String projectPage(Model m) {
        m.addAttribute("singleProject",projectList);
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
    public String project(WebRequest wr, Model m) throws SQLException {
        projectList.clear();

        String tempID = wr.getParameter("projectID");
        int projectID = Integer.parseInt(tempID);
        projectList = pRep.getSingleProject(projectID);
        m.addAttribute("project",projectList);
        return "redirect:/project";
    }
}
