package com.example.demo.Controllers;

import com.example.demo.Services.DatesService;
import com.example.demo.Services.ObjectManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.text.ParseException;

@Controller
public class TaskController {
    ObjectManager objectManager = new ObjectManager();
    DatesService datesService = new DatesService();

    @PostMapping("/task/create")
    public String createTask(WebRequest wr) throws SQLException, ParseException {
        //henter projekt id, start date og end date som en string fra projekt html siden og splitter den ad ved symbolet '¤'
        String tempString = wr.getParameter("createTask");
        String[] tempStringArray = tempString.split("¤");
        String tempProjectID = tempStringArray[0];
        String compareProjectEndDate = tempStringArray[1];
        String compareProjectStartDate = tempStringArray[2];
        //parser tempProjectID til en int
        int projectID = Integer.parseInt(tempProjectID);

        //henter opgave navn, start dato, slut dato, opgavens længde i timer og user id fra projekt html siden
        String taskName = wr.getParameter("taskName");
        String startDate = wr.getParameter("startDate");
        String endDate = wr.getParameter("endDate");
        String tempUserID = wr.getParameter("responsible");
        String tempDuration = wr.getParameter("duration");
        //parser tempUserID og tempDuration til int
        int taskDurationInHours = Integer.parseInt(tempDuration);
        int userID = Integer.parseInt(tempUserID);

        //kalder compareDates metoden og sammenligner de datoer brugeren har indtastet for opgaven med projektets start og slut datoer
        if (!datesService.compareDates(endDate, compareProjectEndDate) || !datesService.compareDates(compareProjectStartDate, startDate)) {
            //hvis brugerens angivne datoer er uden for projektets datoer sættes attributten 'errorMessage' til false
            // og en fejl besked vises på projekt html siden
            objectManager.errorMessage = false;
        } else {
            objectManager.errorMessage = true;
            //hvis brugerens angivne datoer er gyldige, sættes attributten 'errorMessage' til true og opgaven bliver oprettet
            objectManager.tRep.createTask(taskName, startDate, endDate, userID, taskDurationInHours, projectID);
        }

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
        String tempDuration = wr.getParameter("newDuration");
        int taskDurationInHours = Integer.parseInt(tempDuration);

        String tempID = wr.getParameter("getTaskID");
        int taskID = Integer.parseInt(tempID);

        objectManager.tRep.editTask(newTaskName, startDate, endDate, responsibleUserID, tempStatus, taskDurationInHours, taskID);
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