package com.example.demo.Repositories;

import com.example.demo.Models.Project;
import com.example.demo.Services.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    DBConnect connection = new DBConnect();
    List<Project> myProjectList = new ArrayList<>();
    List<Project> sharedProjectList = new ArrayList<>();

    //returner projectList metode
    public List<Project> returnProjectList(PreparedStatement ps, int id, List<Project> list) throws SQLException {
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Project temp = new Project(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDate(3),
                    rs.getInt(4));
            list.add(temp);
        }
        return list;
    }

    //get my projects only
    public List<Project> getMyProjects(int id) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("select distinct projects.projectID, project_name, project_created_date, created_by from projects inner join users_projects on projects.projectID = users_projects.projectID inner join users on users.userID = users_projects.userID where created_by = ?");
        return returnProjectList(ps, id, myProjectList);
    }

    //get projects shared with me and NOT my own projects
    public List<Project> getSharedProjects(int id) throws SQLException{
        PreparedStatement ps = connection.establishConnection().prepareStatement("select distinct projects.projectID, project_name, project_created_date, created_by from projects inner join users_projects on projects.projectID = users_projects.projectID inner join users on users.userID = users_projects.userID where users.userID = ? and created_by != users.userID");
        return returnProjectList(ps, id, sharedProjectList);
    }
}
