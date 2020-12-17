package com.example.demo.Repositories;

import com.example.demo.Models.User;
import com.example.demo.Services.DBConnect;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    DBConnect connection = new DBConnect();
    List<User> teamList = new ArrayList<>();
    List<User> teamListIncludeCreatedBy = new ArrayList<>();

    //metode der indeholder kode der ellers ville være duplikeret
    public int returnUserID(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();

        //returnerer user id
        int id = 0;
        if(rs.next()){
            id = rs.getInt(1);
        }
        return id;
    }

    //metode til at oprette en bruger i databasen
    public void createUser(String username, String password) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO users (username,password) VALUES (?,?)");
        ps.setString(1,username);
        ps.setString(2,password);

        ps.executeUpdate();
    }

    //metode der joiner users og projects tabeller til at hente en bruger ud fra created_by dvs. at finde hvilken bruger der har oprettet et projekt
    public String getUserByID(int id) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("select distinct username from users inner join projects on userID = created_by where created_by = ? ");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();

        //returnerer brugerens username
        String username = "";
        if(rs.next()){
            username = rs.getString(1);
        }
        return username;
    }

    //finder en brugers id ud fra username
    public int getUserIdByUsername(String username) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT userID from users where username = ?");
        ps.setString(1,username);

        return returnUserID(ps);
    }

    //metode til at validere bruger oprettelse - hvis username allerede findes returneres userID og brugeren får en fejlbesked via en if/else statement baseret på om id'et der returneres er 0 eller højere end 0
    public int createUserValidation(String username) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("select userID from users where username = ?");
        ps.setString(1,username);

        return returnUserID(ps);
    }

    //metode til at validere bruger login - hvis username og password findes returneres userID og brugeren logges ind
    public int loginValidation(String username, String password) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("select userID from users where username = ? and password = ?");
        ps.setString(1,username);
        ps.setString(2,password);

        return returnUserID(ps);
    }

    //metode til at finde alle brugere der er tilknyttet et projekt - med undtagelse af den bruger der oprettede projektet
    public List<User> getTeamList(int projectID) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT DISTINCT users.userID, username, password from users inner join project_ownership on users.userID = project_ownership.userID " +
                "inner join projects on project_ownership.projectID = projects.projectID where projects.projectID = ? and users.userID != created_by");
        ps.setInt(1,projectID);
        ResultSet rs = ps.executeQuery();

        //while loop til at læse brugerne ind i en liste
        while (rs.next()) {
            User tmp = new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3));
            teamList.add(tmp);
        }
        //returnerer liste af brugere
        return teamList;
    }

    //metode til at finde alle brugere der er tilknyttet et projekt - inklusiv den bruger der oprettede det
    public List<User> getTeamListIncludeCreatedBy(int projectID) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT DISTINCT users.userID, username, password from users inner join project_ownership on users.userID = project_ownership.userID " +
                "inner join projects on project_ownership.projectID = projects.projectID where projects.projectID = ?");
        ps.setInt(1,projectID);
        ResultSet rs = ps.executeQuery();

        //while loop til at læse brugerne ind i en liste
        while (rs.next()) {
            User tmp = new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3));
            teamListIncludeCreatedBy.add(tmp);
        }
        //returnerer liste af brugere
        return teamListIncludeCreatedBy;
    }
}
