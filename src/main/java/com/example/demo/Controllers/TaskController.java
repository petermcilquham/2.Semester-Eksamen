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

@Controller
public class TaskController {
    TaskRepository tRep = new TaskRepository();
    List<Task> listOfTasks = new ArrayList<>();

    @PostMapping("/createTask")
    public String createTask(WebRequest wr) throws SQLException {
        String taskName = wr.getParameter("taskName");

        //dette giver dagens dato i yyyy/mm/dd
        long millis = System.currentTimeMillis();
        java.sql.Date currentDay = new java.sql.Date(millis);

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
