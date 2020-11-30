package com.example.demo.Repositories;

import com.example.demo.Models.Project;
import com.example.demo.Services.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository {
    DBConnect connection = new DBConnect();
    List<Project> singleProjectList = new ArrayList<>();
    List<Project> myProjectList = new ArrayList<>();
    List<Project> sharedProjectList = new ArrayList<>();

    public void createProject(String projectName, Date currentDay, int createdBy) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO projects (project_name, project_created_date, created_by) VALUES (?,?,?)");
        ps.setString(1,projectName);
        ps.setDate(2,currentDay);
        ps.setInt(3,createdBy);

        ps.executeUpdate();
    }
  
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
        PreparedStatement ps = connection.establishConnection().prepareStatement("select distinct projects.projectID, project_name, project_created_date, created_by from projects inner join project_ownership on projects.projectID = project_ownership.projectID inner join users on users.userID = project_ownership.userID where created_by = ?");
        return returnProjectList(ps, id, myProjectList);
    }

    //get projects shared with me and NOT my own projects
    public List<Project> getSharedProjects(int id) throws SQLException{
        PreparedStatement ps = connection.establishConnection().prepareStatement("select distinct projects.projectID, project_name, project_created_date, created_by from projects inner join project_ownership on projects.projectID = projec_ownership.projectID inner join users on users.userID = project_ownership.userID where users.userID = ? and created_by != users.userID");
        return returnProjectList(ps, id, sharedProjectList);
    }
  
    //delete project
   public void deleteProject(int id) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("DELETE FROM projects WHERE projectID = ?");
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    //get single project
    public List<Project> getSingleProject(int id) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT * FROM projects WHERE projectID = ?");
        return returnProjectList(ps, id, singleProjectList);
    }
}
