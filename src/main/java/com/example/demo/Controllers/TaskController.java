package com.example.demo.Controllers;

import com.example.demo.Repositories.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.GregorianCalendar;

@Controller
public class TaskController {
    TaskRepository tRep = new TaskRepository();

    @PostMapping("/createTask")
    public String createTask(WebRequest wr) throws SQLException {
        String taskName = wr.getParameter("taskName");

        //dette giver dagens dato i yyyy/mm/dd
        long millis = System.currentTimeMillis();
        java.sql.Date currentDay = new java.sql.Date(millis);


        tRep.createTask(taskName, currentDay, endDate);
        return "redirect:/project";
    }
}
