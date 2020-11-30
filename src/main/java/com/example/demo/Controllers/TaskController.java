package com.example.demo.Controllers;

import com.example.demo.Models.Task;
import com.example.demo.Repositories.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.Date;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class TaskController {
    TaskRepository tRep = new TaskRepository();
    List<Task> listOfTasks = new ArrayList<>();

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
        String newTaskName = wr.getParameter("taskName");
        String startDate = wr.getParameter("startDate");
        String endDate = wr.getParameter("endDate");
        String tmpID = wr.getParameter("taskID");
        int taskID = Integer.parseInt(tmpID);

        tRep.editTask(newTaskName, startDate, endDate, taskID);
        return "redirect:/project";
    }

}
