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
    //fejlbesked booleans
    private boolean errorLogin;
    private boolean errorCreateUser;
    private boolean successCreatedUser;

    //index.html side
    @GetMapping("/")
    public String index(Model m){
        //fejlbesked booleans sendes til index.html siden via model
        m.addAttribute("errorLogin",errorLogin);
        m.addAttribute("errorCreateUser",errorCreateUser);
        m.addAttribute("successCreatedUser",successCreatedUser);
        return "index";
    }

    //opret en bruger
    @PostMapping("/createUser")
    public String createUser(WebRequest wr) throws SQLException {
        //username og password hentes fra bruger input på html siden
        String username = wr.getParameter("createUsername");
        String password = wr.getParameter("createPassword");

        //username bliver valideret
        int userID = objectManager.uRep.createUserValidation(username);
        if(userID>0){
            //hvis et userID blev returneret, dvs. userID er højere end 0, så fandtes det username allerede og brugeren får en fejlbesked
            errorCreateUser = true;
            successCreatedUser = false;
        } else {
            //hvis userID er 0, så bliver den nye bruger oprettet
            objectManager.uRep.createUser(username,password);
            successCreatedUser = true;
            errorCreateUser = false;
        }
        return "redirect:/";
    }

    //login post mapping
    @PostMapping("/login")
    public String login(HttpSession session, WebRequest wr) throws SQLException {
        //brugerens input hentes ind fra html
        String username = wr.getParameter("inputUsername");
        String password = wr.getParameter("inputPassword");

        //validerer login
        int userID = objectManager.uRep.loginValidation(username, password);

        //hvis userID er højere end 0, så findes der en match i databasen for det username og password brugeren indtastede og brugeren bliver logget ind
        if(userID>0){
            //starter en session
            session.setAttribute("userID", userID);
            return "redirect:/main";
        } else {
            //hvis userID er 0, så findes der ikke en match. errorLogin sættes til true for at vise en fejlbesked og brugeren logges ikke ind
            errorLogin = true;
            return "redirect:/";
        }
    }

    //log ud knap
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        //slutter en session og sætter errorLogin attribut til false så vi sikrer at fejlbesked ikke vises når brugeren kommer tilbage til forsiden
        session.invalidate();
        errorLogin = false;
        return "redirect:/";
    }
}
