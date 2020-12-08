package com.example.demo.Controllers;

import com.example.demo.Services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class MainController {
    ObjectManager objectManager = new ObjectManager();

    @GetMapping("/main")
    public String main(Model m, HttpSession session) throws SQLException {
        try {
            objectManager.clearLists();
            objectManager.myProjectList = objectManager.pRep.getMyProjects((Integer) session.getAttribute("userID"));
            objectManager.sharedProjectList = objectManager.pRep.getSharedProjects((Integer) session.getAttribute("userID"));
            m.addAttribute("myProjectList", objectManager.myProjectList);
            m.addAttribute("sharedProjectList", objectManager.sharedProjectList);
            return "main";

        } catch(NullPointerException e) {
            return "redirect:/";
            }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/backtomain")
    public String backToMain() {
        objectManager.clearLists();
        return "redirect:/main";
    }
  
    @PostMapping("/project/create")
    public String createProject(WebRequest wr, HttpSession session) throws SQLException {
        objectManager.clearLists();
        String projectName = wr.getParameter("projectName");

        //dette giver dagens dato i yyyy/mm/dd
        long millis = System.currentTimeMillis();
        java.sql.Date currentDay = new java.sql.Date(millis);

        int userID = (Integer) session.getAttribute("userID");
        String endDate = wr.getParameter("endDate");

        objectManager.pRep.createProject(projectName, currentDay, endDate, userID);
        return "redirect:/main";
    }
}
