package com.example.demo.Controllers;

import com.example.demo.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@Controller
public class UserController {
    UserRepository uRep = new UserRepository();

    @GetMapping("/")
    public String index(Model m){
        m.addAttribute("createUser",m);
        return "index";
    }

    @PostMapping("/createUser")
    public String createUser(WebRequest wr) throws SQLException {
        String username = wr.getParameter("createUsername");
        String password = wr.getParameter("createPassword");

        uRep.createUser(username,password);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(){
        System.out.println("User loggd in");
        //ur.login metode (test for username/password er optaget)
        return "redirect:/main";
    }
}
