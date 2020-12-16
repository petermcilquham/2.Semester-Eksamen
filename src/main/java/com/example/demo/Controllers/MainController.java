package com.example.demo.Controllers;

import com.example.demo.Services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
            m.addAttribute("errorMessage",objectManager.errorMessage);
            return "main";
        } catch(NullPointerException e) {
            return "redirect:/";
        }
    }

    @PostMapping("/backtomain")
    public String backToMain() {
        objectManager.errorMessage = true;
        objectManager.clearLists();
        return "redirect:/main";
    }
}
