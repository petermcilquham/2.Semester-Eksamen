package com.example.demo.Controllers;

import com.example.demo.Services.ClearLists;
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
    ClearLists clearLists = new ClearLists();

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

        int id = objectManager.uRep.loginValidation(username, password);
        System.out.println("logged in as id:" + id + ", " + username);

        if(id>0){
            session.setAttribute("userID", id);
            return "redirect:/main";
        } else {
            //error msg i html: "username or password not correct. try again"
            return "redirect:/";
        }
    }
}
