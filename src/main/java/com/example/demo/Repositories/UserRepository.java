package com.example.demo.Repositories;

import com.example.demo.Models.Project;
import com.example.demo.Services.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    List<Project> projectList = new ArrayList<>();
    DBConnect connection = new DBConnect();

    public void createUser(String username, String password) throws SQLException {

        PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO users (username,password) VALUES (?,?)");
        ps.setString(1,username);
        ps.setString(2,password);

        ps.executeUpdate();
    }

    public void getUsersProjects(int id) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("select projects.projectID, project_name, project_created_date " +
                "from projects inner join users_projects on projects.projectID = users_projects.projectID " +
                "inner join users on users.userID = users_projects.userID where users.userID = ?");
        ps.setInt(1,id);

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Project  temp = new Project(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDate(3));
            projectList.add(temp);
        }
        System.out.println(projectList.toString());
    }

}