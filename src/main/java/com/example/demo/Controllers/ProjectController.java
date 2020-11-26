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
public class ProjectController {
    UserRepository ur = new UserRepository();
    ProjectRepository pr = new ProjectRepository();
    List<Project> projectList = new ArrayList<>();

    @GetMapping("/project")
    public String projectPage(Model m) throws SQLException {
        m.addAttribute("singleProject",projectList);
        return "project";
    }

    @PostMapping("/project/delete")
    public String deleteProject(WebRequest wr) throws SQLException {
        String tempID = wr.getParameter("deleteProject");
        int projectID = Integer.parseInt(tempID);
        pr.deleteProject(projectID);
        return "redirect:/main";
    }

    @PostMapping("/getproject")
    public String project(WebRequest wr) throws SQLException {
        projectList.clear();
        String tempID = wr.getParameter("projectID");
        int projectID = Integer.parseInt(tempID);
        projectList = pr.getSingleProject(projectID);
        return "redirect:/project";
    }
}
