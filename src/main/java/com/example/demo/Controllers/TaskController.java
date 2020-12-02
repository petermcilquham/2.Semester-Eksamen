package com.example.demo.Controllers;

import com.example.demo.Services.ClearLists;
import com.example.demo.Services.ObjectManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.text.ParseException;

@Controller
public class TaskController {
    ObjectManager objectManager = new ObjectManager();
    ClearLists clearLists = new ClearLists();

    @PostMapping("/createTask")
    public String createTask(WebRequest wr) throws SQLException, ParseException {
        String taskName = wr.getParameter("taskName");
        String tempID = wr.getParameter("createTask");
        int projectID = Integer.parseInt(tempID);

        //dette giver dagens dato i yyyy/mm/dd
        long millis = System.currentTimeMillis();
        java.sql.Date currentDay = new java.sql.Date(millis);

        //Vi modtager endDate i String format fra html og omdanner den til en sql.date, så den kan indsættes i databasen.
        String endDate = wr.getParameter("endDate");
        java.sql.Date.valueOf(endDate);
      
        //tRep.createTask(taskName, currentDay, endDate);
        return "redirect:/project";
    }

    @PostMapping("/project/edit")
    public String editTask(WebRequest wr) throws SQLException {
        String newTaskName = wr.getParameter("newTaskName");
        String startDate = wr.getParameter("newStartDate");
        String endDate = wr.getParameter("newEndDate");
        int taskResponsible = Integer.parseInt(wr.getParameter("newResponsible"));

        boolean tempStatus = Boolean.parseBoolean(wr.getParameter("newCompletionStatus"));
        System.out.println(tempStatus);


        String tempID = wr.getParameter("getTaskID");
        int taskID = Integer.parseInt(tempID);

        objectManager.tRep.editTask(newTaskName, startDate, endDate, taskResponsible, tempStatus, taskID);
        return "redirect:/project";
    }

}
