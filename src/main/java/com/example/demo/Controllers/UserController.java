package com.example.demo.Controllers;

import com.example.demo.Services.ObjectManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class UserController {
    ObjectManager objectManager = new ObjectManager();

    @GetMapping("/")
    public String index(Model m){
        m.addAttribute("createUser",m);
        return "index";
    }

    @PostMapping("/createUser")
    public String createUser(WebRequest wr) throws SQLException {
        String username = wr.getParameter("createUsername");
        String password = wr.getParameter("createPassword");
        objectManager.uRep.createUser(username,password);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(HttpSession session, WebRequest wr) throws SQLException {
        String username = wr.getParameter("inputUsername");
        String password = wr.getParameter("inputPassword");

        int userID = objectManager.uRep.loginValidation(username, password);
        System.out.println("logged in as id:" + userID + ", " + username);

        if(userID>0){
            session.setAttribute("userID", userID);
            System.out.println(session.getAttribute("userID"));
            return "redirect:/main";
        } else {
            session.setAttribute("userID",0);
            session.getAttribute("userID");
            return "redirect:/";
        }
    }

    @GetMapping("/gantt")
    public String test(){
        return "test";
    }
}
