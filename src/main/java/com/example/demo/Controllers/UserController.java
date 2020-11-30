package com.example.demo.Controllers;

import com.example.demo.Models.Project;
import com.example.demo.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    List<Project> projectList = new ArrayList<>();
    UserRepository ur = new UserRepository();

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/main")
    public String main(Model m) throws SQLException {
        m.addAttribute("projectList",ur.getUsersProjects(1));
        return "main";
    }

    @PostMapping("/login")
    public String login(WebRequest wr) throws SQLException {
        String username = wr.getParameter("loginUsername");
        String password = wr.getParameter("loginUsername");

        ur.login(username,password);
        return "redirect:/main";
    }

    @PostMapping("/createUser")
    public String createUser(WebRequest wr) throws SQLException {
        String username = wr.getParameter("createUsername");
        String password = wr.getParameter("createPassword");

        ur.createUser(username,password);

        return "redirect:/";
    }
}
