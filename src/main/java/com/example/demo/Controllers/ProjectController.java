package com.example.demo.Controllers;

import com.example.demo.Models.Task;
import com.example.demo.Models.User;
import com.example.demo.Services.CompareDates;
import com.example.demo.Services.ObjectManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@Controller
public class ProjectController {
    ObjectManager objectManager = new ObjectManager();
    CompareDates compareDates = new CompareDates();
    int projectID = 0;

    @GetMapping("/project")
    public String projectPage(Model m, HttpSession session, WebRequest wr) {
        try {
            if (!session.getAttribute("userID").equals("")) {

                objectManager.clearLists();
                objectManager.pRep.getSingleProject(projectID);
                objectManager.tRep.getTaskList(projectID);
                objectManager.uRep.getTeamList(projectID);
                objectManager.uRep.getTeamListIncludeCreatedBy(projectID);
                m.addAttribute("singleProject", objectManager.singleProjectList);
                m.addAttribute("taskList", objectManager.taskList);
                m.addAttribute("teamList", objectManager.teamList);
                m.addAttribute("TeamListIncludeCreatedBy", objectManager.teamListIncludeCreatedBy);
                m.addAttribute("errorMessage", objectManager.errorMessage);
                return "project";
            }
        } catch (NullPointerException | SQLException e) {
            return "redirect:/";
        }
        return "project";
    }

    @PostMapping("/project/create")
    public String createProject(WebRequest wr, HttpSession session) throws SQLException {
        objectManager.clearLists();
        String projectName = wr.getParameter("projectName");

        //dette giver dagens dato i yyyy/mm/dd
        long millis = System.currentTimeMillis();
        java.sql.Date currentDay = new java.sql.Date(millis);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String compareCurrentDay = simpleDateFormat.format(currentDay);

        int userID = (Integer) session.getAttribute("userID");
        String endDate = wr.getParameter("endDate");

        objectManager.errorMessage = compareDates.compareDates(compareCurrentDay, endDate);

        objectManager.pRep.createProject(projectName, currentDay, endDate, userID);
        return "redirect:/main";
    }

    @PostMapping("/project/get")
    public String getProject(WebRequest wr) throws SQLException {
        objectManager.clearLists();
        String tempID = wr.getParameter("projectID");
        projectID = Integer.parseInt(tempID);
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
        objectManager.clearLists();
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
