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
    public String projectPage(Model m, Model m2) {
        m.addAttribute("singleProject",objectManager.singleProjectList);
        m2.addAttribute("taskList",objectManager.listOfTasks);
        return "project";
    }

    @PostMapping("/project/get")
    public String getProject(WebRequest wr) throws SQLException {
        String tempID = wr.getParameter("projectID");
        int projectID = Integer.parseInt(tempID);
        objectManager.singleProjectList.clear();
        objectManager.listOfTasks.clear();
        objectManager.singleProjectList = objectManager.pRep.getSingleProject(projectID);
        objectManager.listOfTasks = objectManager.tRep.getTaskList(projectID);
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
        String tempUserID = wr.getParameter("shareUserID");
        String tempProjectID = wr.getParameter("shareProjectID");
        int userID = Integer.parseInt(tempUserID);
        int projectID = Integer.parseInt(tempProjectID);

        objectManager.pRep.shareProject(userID,projectID);
        return "redirect:/project";

    }

}
