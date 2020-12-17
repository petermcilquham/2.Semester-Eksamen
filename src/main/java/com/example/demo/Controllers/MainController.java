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

    //main.html side
    @GetMapping("/main")
    public String main(Model m, HttpSession session) throws SQLException {
        try {
            //tømmer først alle lister
            objectManager.clearLists();

            //henter derefter to lister af projekter ud fra den bruger der er logget ind
            objectManager.myProjectList = objectManager.pRep.getMyProjects((Integer) session.getAttribute("userID"));
            objectManager.sharedProjectList = objectManager.pRep.getSharedProjects((Integer) session.getAttribute("userID"));

            //sendder nødvendig data til html siden via model
            m.addAttribute("myProjectList", objectManager.myProjectList);
            m.addAttribute("sharedProjectList", objectManager.sharedProjectList);
            m.addAttribute("errorMessage",objectManager.errorMessage);
            return "main";
        } catch(NullPointerException e) {
            //hvis ingen bruger er logget ind så vil denne catch fange fejlbeskeden og sende brugeren til forsiden
            return "redirect:/";
        }
    }

    //tilbage knap - sender brugeren til main.html side
    @PostMapping("/backtomain")
    public String backToMain() {
        //nulstiller errorMessage variablen og tømmer lister
        objectManager.errorMessage = true;
        objectManager.clearLists();
        return "redirect:/main";
    }
}
