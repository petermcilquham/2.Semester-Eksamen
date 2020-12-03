package com.example.demo.Controllers;

import com.example.demo.Services.ObjectManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.text.ParseException;

@Controller
public class TaskController {
    ObjectManager objectManager = new ObjectManager();

    @PostMapping("/task/create")
    public String createTask(WebRequest wr) throws SQLException, ParseException {
        String tempProjectID = wr.getParameter("createTask");
        int projectID = Integer.parseInt(tempProjectID);

        String taskName = wr.getParameter("taskName");

        //Vi modtager endDate i String format fra html og omdanner den til en sql.date, så den kan indsættes i databasen.
        String startDate = wr.getParameter("startDate");
        String endDate = wr.getParameter("endDate");

        String tempUserID = wr.getParameter("responsible");
        int userID = Integer.parseInt(tempUserID);

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
        System.out.println(tempStatus);


        String tempID = wr.getParameter("getTaskID");
        int taskID = Integer.parseInt(tempID);

        objectManager.tRep.editTask(newTaskName, startDate, endDate, responsibleUserID, tempStatus, taskID);
        return "redirect:/project";
    }
}
