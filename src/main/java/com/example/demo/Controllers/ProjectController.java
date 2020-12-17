package com.example.demo.Controllers;

import com.example.demo.Services.DatesService;
import com.example.demo.Services.ObjectManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@Controller
public class ProjectController {
    ObjectManager objectManager = new ObjectManager();
    DatesService datesService = new DatesService();

    //denne int bliver givet en værdi når brugeren vælger at se et projekt fra listen af projekter på main.html siden. Dette sker i /project/get post mappingen længere nede
    private int projectID = 0;

    //project.html side
    @GetMapping("/project")
    public String projectPage(Model m, HttpSession session) {
        try {
            //if statement der tjekker om en bruger er logget ind - hvis session er tom bliver det fanget af catch og brugeren sendes til forsiden
            if (!session.getAttribute("userID").equals("")) {
                //tømmer lister
                objectManager.clearLists();

                //projektet, opgaverne og listerne af brugere der er tilknyttet bliver hentet fra objectMandager klassen
                objectManager.pRep.getSingleProject(projectID);
                objectManager.tRep.getTaskList(projectID);
                objectManager.uRep.getTeamList(projectID);
                objectManager.uRep.getTeamListIncludeCreatedBy(projectID);

                //nødvendig data bliver sendt til html siden via model
                m.addAttribute("singleProject", objectManager.singleProjectList);
                m.addAttribute("taskList", objectManager.taskList);
                m.addAttribute("teamList", objectManager.teamList);
                m.addAttribute("TeamListIncludeCreatedBy", objectManager.teamListIncludeCreatedBy);
                m.addAttribute("errorMessage", objectManager.errorMessage);
                return "project";
            }
        } catch (NullPointerException | SQLException e) {
            return "redirect:/";
        }
        return "project";
    }

    //denne postmapping hører til en knap på main siden, knappen hedder 'View project' og er den knap man trykker på når man vil se et projekts opgaver
    @PostMapping("/project/get")
    public String getProject(WebRequest wr) throws SQLException {
        //tømmer lister
        objectManager.clearLists();

        //henter projekt id fra html siden og parser den til en int
        String tempID = wr.getParameter("projectID");
        projectID = Integer.parseInt(tempID);

        //henter data fra databasen og fylder det i lister
        objectManager.teamList = objectManager.uRep.getTeamList(projectID);
        objectManager.teamListIncludeCreatedBy = objectManager.uRep.getTeamListIncludeCreatedBy(projectID);
        objectManager.singleProjectList = objectManager.pRep.getSingleProject(projectID);
        objectManager.taskList = objectManager.tRep.getTaskList(projectID);
        return "redirect:/project";
    }

    //post mapping til at oprette et projekt
    @PostMapping("/project/create")
    public String createProject(WebRequest wr, HttpSession session) throws SQLException {
        //nulstiller alle array lister
        objectManager.clearLists();

        //henter projekt navn og slut dato fra project.html siden
        String projectName = wr.getParameter("projectName");
        String endDate = wr.getParameter("endDate");

        //henter user id fra http session
        int userID = (Integer) session.getAttribute("userID");

        //dette giver dagens dato i yyyy/mm/dd format
        long millis = System.currentTimeMillis();
        java.sql.Date currentDay = new java.sql.Date(millis);
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String compareCurrentDay = simpleDateFormat.format(currentDay);

        //kalder compareDates metoden til at tjekke at slut datoen for projektet er efter den nuværende dato
        objectManager.errorMessage = datesService.compareDates(compareCurrentDay, endDate);
        if(objectManager.errorMessage){
            objectManager.pRep.createProject(projectName, currentDay, endDate, userID);
        }
        return "redirect:/main";
    }

    //sletter et projekt og sender brugeren til main.html siden
    @PostMapping("/project/delete")
    public String deleteProject(WebRequest wr) throws SQLException {
        //projektets id hentes fra html siden
        String tempID = wr.getParameter("deleteProject");
        int projectID = Integer.parseInt(tempID);

        //databasen opdateres og lister tømmes
        objectManager.pRep.deleteProject(projectID);
        objectManager.clearLists();

        return "redirect:/main";
    }

    //'deler' et projekt med en bruger, dvs. tilknytter ham til et projekt
    @PostMapping("/project/share")
    public String shareProject(WebRequest wr) throws SQLException {
        //brugeren vælger et username - den anden bruger projektet skal deles med
        String tempUsername = wr.getParameter("shareUsername");
        int userID = objectManager.uRep.getUserIdByUsername(tempUsername);

        //projektet der skal deles' id hentes fra html siden
        String tempProjectID = wr.getParameter("shareProjectID");
        int projectID = Integer.parseInt(tempProjectID);

        //databasen opdateres
        objectManager.pRep.shareProject(userID,projectID);

        return "redirect:/project";
    }
}
