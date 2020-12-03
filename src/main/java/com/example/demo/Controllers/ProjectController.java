package com.example.demo.Controllers;

import com.example.demo.Services.ObjectManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class ProjectController {
    ObjectManager objectManager = new ObjectManager();

    @GetMapping("/project")
    public String projectPage(Model m, HttpSession session) {
        try {
            if (!session.getAttribute("userID").equals("")) {
                m.addAttribute("singleProject", objectManager.singleProjectList);
                m.addAttribute("taskList", objectManager.taskList);
                m.addAttribute("teamList", objectManager.teamList);
                m.addAttribute("TeamListIncludeCreatedBy", objectManager.teamListIncludeCreatedBy);
                return "project";
            }
        } catch (NullPointerException e) {
            return "redirect:/";
        }
        return "project";
    }

    @PostMapping("/project/get")
    public String getProject(WebRequest wr) throws SQLException {
        objectManager.clearLists();
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
        String tempUsername = wr.getParameter("shareUsername");
        int userID = objectManager.uRep.getUserIdByUsername(tempUsername);

        String tempProjectID = wr.getParameter("shareProjectID");
        int projectID = Integer.parseInt(tempProjectID);

        objectManager.pRep.shareProject(userID,projectID);
        return "redirect:/project";
    }
}
