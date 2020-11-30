package com.example.demo.Controllers;

import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.ClearLists;
import com.example.demo.Services.ObjectManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String login(HttpServletRequest wr, HttpServletResponse re){
      //uRep.login metode (test for username/password er optaget)
        /*
        Request username = wr.getParameter("inputUsername");
        Response response = re.addCookie();
        cookie.doPost(username, response);
        System.out.println("User loggd in");
         */
      
        return "redirect:/main";
    }
}
