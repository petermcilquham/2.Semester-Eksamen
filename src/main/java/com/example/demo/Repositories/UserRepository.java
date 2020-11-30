package com.example.demo.Repositories;

import com.example.demo.Models.Project;
import com.example.demo.Models.User;
import com.example.demo.Services.DBConnect;
import com.mysql.cj.protocol.Resultset;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    List<Project> projectList = new ArrayList<>();
    DBConnect connection = new DBConnect();
    User currentLogin = new User(0,null,null);

    public User login(String username, String password) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
        ps.setString(1,username);
        ps.setString(2,password);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            User temp = new User(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3));
            currentLogin = temp;
        }
        return currentLogin;
    }

    public void createUser(String username, String password) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO users (username,password) VALUES (?,?)");
        ps.setString(1,username);
        ps.setString(2,password);

        ps.executeUpdate();
    }

    public List<Project> getUsersProjects(int id) throws SQLException {
        projectList.clear();
        PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT projects.projectID, project_name, project_created_date " +
                "FROM projects INNER JOIN users_projects ON projects.projectID = users_projects.projectID " +
                "INNER JOIN users ON users.userID = users_projects.userID WHERE users.userID = ?");
        ps.setInt(1,id);

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Project  temp = new Project(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDate(3));
            projectList.add(temp);
        }
        return projectList;
    }

}