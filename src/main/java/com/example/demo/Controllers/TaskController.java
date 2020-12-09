package com.example.demo.Controllers;

import com.example.demo.Services.CompareDates;
import com.example.demo.Services.ObjectManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.text.ParseException;

@Controller
public class TaskController {
    ObjectManager objectManager = new ObjectManager();
    CompareDates compareDates = new CompareDates();

    @PostMapping("/task/create")
    public String createTask(WebRequest wr) throws SQLException, ParseException {
        //henter projekt id, start date og end date som string og splitter den ad
        String tempString = wr.getParameter("createTask");
        String[] tempStringArray = tempString.split("¤");
        String tempProjectID = tempStringArray[0];
        String compareProjectEndDate = tempStringArray[1];
        String compareProjectStartDate = tempStringArray[2];

        int projectID = Integer.parseInt(tempProjectID);

        String taskName = wr.getParameter("taskName");

        // Vi modtager endDate i String format fra html og omdanner den til en sql.date, så den kan indsættes i databasen.
        String startDate = wr.getParameter("startDate");
        String endDate = wr.getParameter("endDate");

        String tempUserID = wr.getParameter("responsible");
        int userID = Integer.parseInt(tempUserID);

        if (!compareDates.compareDates(endDate, compareProjectEndDate) || !compareDates.compareDates(compareProjectStartDate, startDate) ) {
            objectManager.errorMessage = false;
            System.out.println("hej");
        } else {
            objectManager.errorMessage = true;
            objectManager.tRep.createTask(taskName, startDate, endDate, userID, projectID);
        }
        return "redirect:/project";
    }

    @PostMapping("/task/edit")
    public String editTask(WebRequest wr) throws SQLException {
        String newTaskName = wr.getParameter("newTaskName");
        String startDate = wr.getParameter("newStartDate");
        String endDate = wr.getParameter("newEndDate");
        String tempUserID = wr.getParameter("newResponsible");
        System.out.println("rsponsible: " + tempUserID);
        int responsibleUserID = Integer.parseInt(tempUserID);
        boolean tempStatus = Boolean.parseBoolean(wr.getParameter("newCompletionStatus"));

        System.out.println("status: " + tempStatus);

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
