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

    //opretter en opgave
    @PostMapping("/task/create")
    public String createTask(WebRequest wr) throws SQLException, ParseException {
        //henter projekt id, start date og end date som en string fra project.html siden og splitter den ad ved symbolet '¤'
        String tempString = wr.getParameter("createTask");
        String[] tempStringArray = tempString.split("¤");
        String tempProjectID = tempStringArray[0];
        String compareProjectEndDate = tempStringArray[1];
        String compareProjectStartDate = tempStringArray[2];

        //parser tempProjectID til en int
        int projectID = Integer.parseInt(tempProjectID);

        //henter opgave navn, start dato, slut dato, opgavens længde i timer og user id fra project.html siden
        String taskName = wr.getParameter("taskName");
        String startDate = wr.getParameter("startDate");
        String endDate = wr.getParameter("endDate");
        String tempUserID = wr.getParameter("responsible");
        String tempDuration = wr.getParameter("duration");

        //parser tempUserID og tempDuration til ints
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

    //post mapping metode til at ændre i en opgave
    @PostMapping("/task/edit")
    public String editTask(WebRequest wr) throws SQLException {
        //først hentes alle data fra html siden
        String newTaskName = wr.getParameter("newTaskName");
        String startDate = wr.getParameter("newStartDate");
        String endDate = wr.getParameter("newEndDate");
        String tempUserID = wr.getParameter("newResponsible");
        String tempStatus = wr.getParameter("newCompletionStatus");
        String tempDuration = wr.getParameter("newDuration");
        String tempID = wr.getParameter("getTaskID");

        //diverse variabler parses til ints og boolean
        int responsibleUserID = Integer.parseInt(tempUserID);
        int taskDurationInHours = Integer.parseInt(tempDuration);
        int taskID = Integer.parseInt(tempID);
        boolean status = Boolean.parseBoolean(tempStatus);

        //databasen opdateres
        objectManager.tRep.editTask(newTaskName, startDate, endDate, responsibleUserID, status, taskDurationInHours, taskID);

        return "redirect:/project";
    }

    //slet en opgave
    @PostMapping("/task/delete")
    public String deleteTask(WebRequest wr) throws SQLException {
        //opgave id hentes fra html side
        String tempTaskID = wr.getParameter("deleteTask");
        int taskID = Integer.parseInt(tempTaskID);

        //databasen opdateres
        objectManager.tRep.deleteTask(taskID);

        return "redirect:/project";
    }
}