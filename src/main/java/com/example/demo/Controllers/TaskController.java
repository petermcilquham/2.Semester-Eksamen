package com.example.demo.Controllers;

import com.example.demo.Services.ObjectManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@Controller
public class TaskController {
    ObjectManager objectManager = new ObjectManager();

    @PostMapping("/task/create")
    public String createTask(WebRequest wr) throws SQLException{
        String tempProjectID = wr.getParameter("createTask");
        int projectID = Integer.parseInt(tempProjectID);

        String taskName = wr.getParameter("taskName");

        //Vi modtager endDate i String format fra html og omdanner den til en sql.date, så den kan indsættes i databasen.
        String startDate = wr.getParameter("startDate");
        String endDate = wr.getParameter("endDate");

        String tempUserID = wr.getParameter("responsible");
        int userID = Integer.parseInt(tempUserID);

        String projectEndDate = wr.getParameter("");

        objectManager.tRep.createTask(taskName, startDate, endDate, userID, projectID);
        return "redirect:/project";
    }

    @PostMapping("/task/edit")
    public String editTask(WebRequest wr) throws SQLException {
        String newTaskName = wr.getParameter("newTaskName");
        String startDate = wr.getParameter("newStartDate");
        String endDate = wr.getParameter("newEndDate");
        String tempUserID = wr.getParameter("newResponsible");
        int responsibleUserID = Integer.parseInt(tempUserID);
        boolean tempStatus = Boolean.parseBoolean(wr.getParameter("newCompletionStatus"));

        String tempID = wr.getParameter("getTaskID");
        int taskID = Integer.parseInt(tempID);

        objectManager.tRep.editTask(newTaskName, startDate, endDate, responsibleUserID, tempStatus, taskID);
        return "redirect:/project";
    }

    @PostMapping("/task/delete")
    public String deleteTask(WebRequest wr) throws SQLException {
        String tempTaskID = wr.getParameter("deleteTask");
        int taskID = Integer.parseInt(tempTaskID);

        objectManager.tRep.deleteTask(taskID);
        return "redirect:/project";
    }



















}
