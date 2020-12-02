package com.example.demo.Controllers;

import com.example.demo.Services.ClearLists;
import com.example.demo.Services.ObjectManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@Controller
public class ProjectController {
    ObjectManager objectManager = new ObjectManager();
    ClearLists clearLists = new ClearLists();

    @GetMapping("/project")
    public String projectPage(Model m) {
        m.addAttribute("singleProject",objectManager.singleProjectList);
        m.addAttribute("taskList",objectManager.taskList);
        m.addAttribute("teamList",objectManager.teamList);
        m.addAttribute("TeamListIncludeCreatedBy",objectManager.teamListIncludeCreatedBy);
        return "project";
    }

    @PostMapping("/project/get")
    public String getProject(WebRequest wr) throws SQLException {
        clearLists.clearLists();
        String tempID = wr.getParameter("projectID");
        int projectID = Integer.parseInt(tempID);
        objectManager.teamList = objectManager.uRep.getTeamList(projectID);
        objectManager.teamListIncludeCreatedBy = objectManager.uRep.getTeamListIncludeCreatedBy(projectID);
        objectManager.singleProjectList = objectManager.pRep.getSingleProject(projectID);
        objectManager.taskList = objectManager.tRep.getTaskList(projectID);
        return "redirect:/project";
    }

    @PostMapping("/project/delete")
    public String deleteProject(WebRequest wr) throws SQLException {
        String tempID = wr.getParameter("deleteProject");
        int projectID = Integer.parseInt(tempID);
        objectManager.pRep.deleteProject(projectID);
        return "redirect:/main";
    }

    @PostMapping("/project/share")
    public String shareProject(WebRequest wr) throws SQLException {
        String tempUserID = wr.getParameter("teamList");
        String tempProjectID = wr.getParameter("shareProjectID");
        int userID = Integer.parseInt(tempUserID);
        int projectID = Integer.parseInt(tempProjectID);
        objectManager.pRep.shareProject(userID,projectID);
        return "redirect:/project";
    }

}
