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

    //create user
    public void createUser(String username, String password) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO users (username,password) VALUES (?,?)");
        ps.setString(1,username);
        ps.setString(2,password);

        ps.executeUpdate();
    }

    //get user by created_by id
    public String getUserByID(int id) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("select distinct username from users inner join projects on userID = created_by where created_by = ? ");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();

        String username = "";
        if(rs.next()){
            username = rs.getString(1);
        }
        return username;
    }

    //validate user login method
    public int loginValidation(String username, String password) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("select userID from users where username = ? and password = ?");
        ps.setString(1,username);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        int id = 0;
        if(rs.next()){
            id = rs.getInt(1);
        }
        return id;
    }

    //get my team list
    public List<User> getTeamList(int projectID) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT DISTINCT users.userID, username, password from users inner join project_ownership on users.userID = project_ownership.userID " +
                "inner join projects on project_ownership.projectID = projects.projectID where projects.projectID = ? and users.userID != created_by");
        ps.setInt(1,projectID);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User tmp = new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3));
            teamList.add(tmp);
        }
        return teamList;
    }

    //get my team list including owner of project
    public List<User> getTeamListIncludeCreatedBy(int projectID) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT DISTINCT users.userID, username, password from users inner join project_ownership on users.userID = project_ownership.userID " +
                "inner join projects on project_ownership.projectID = projects.projectID where projects.projectID = ?");
        ps.setInt(1,projectID);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User tmp = new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3));
            teamListIncludeCreatedBy.add(tmp);
        }
        return teamListIncludeCreatedBy;
    }
}
