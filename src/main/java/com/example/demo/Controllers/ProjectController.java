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
    UserRepository uRep = new UserRepository();
    ProjectRepository pRep = new ProjectRepository();
    List<Project> projectList = new ArrayList<>();

    @GetMapping("/project")
    public String projectPage(Model m) throws SQLException {
        //projectList = pRep.getUsersProjects(1);
        System.out.println("liste: " + projectList.size());
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

}
