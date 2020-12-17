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

    //metode der indeholder kode der ellers ville være duplikeret - returnerer en liste af projekter
    public List<Project> returnProjectList(PreparedStatement ps, int userID, List<Project> list) throws SQLException {
        ps.setInt(1,userID);
        ResultSet rs = ps.executeQuery();

        //while loop til at læse projekter ind i en liste
        while(rs.next()){
            Project temp = new Project(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDate(3),
                    rs.getString(4),
                    rs.getInt(5));
            list.add(temp);
        }
        //returnerer en liste af projekter
        return list;
    }

    //opretter et projekt i databasen
    public void createProject(String projectName, Date currentDay, String endDate, int createdBy) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO projects (project_name, project_created_date, project_end_date, created_by) VALUES (?,?,?,?)");
        ps.setString(1,projectName);
        ps.setDate(2,currentDay);
        ps.setString(3,endDate);
        ps.setInt(4,createdBy);

        ps.executeUpdate();
    }

    //metode til at hente de projekter som en bruger har oprettet
    public List<Project> getMyProjects(int userID) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("select distinct projects.projectID, project_name, project_created_date, project_end_date, created_by from projects" +
                " inner join project_ownership on projects.projectID = project_ownership.projectID inner join users on users.userID = project_ownership.userID where created_by = ?");

        return returnProjectList(ps, userID, myProjectList);
    }

    //metode til at hente de projekter en bruger har adgang til - men som brugeren ikke selv har oprettet
    public List<Project> getSharedProjects(int userID) throws SQLException{
            PreparedStatement ps = connection.establishConnection().prepareStatement("select distinct projects.projectID, project_name, project_created_date, project_end_date, created_by " +
                    "from projects inner join project_ownership on projects.projectID = project_ownership.projectID inner join users " +
                    "on users.userID = project_ownership.userID where users.userID = ? and created_by != users.userID");

            return returnProjectList(ps, userID, sharedProjectList);
    }

    //slet et projekt
   public void deleteProject(int projectID) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("DELETE FROM projects WHERE projectID = ?");
        ps.setInt(1,projectID);
        ps.executeUpdate();
    }

    //hent et enkelt projekt
    public List<Project> getSingleProject(int projectID) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT * FROM projects WHERE projectID = ?");

        return returnProjectList(ps, projectID, singleProjectList);
    }

    //tilknyt et projekt til en bruger
    public void shareProject(int userID, int projectID) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO project_ownership (userID, projectID) values (?,?)");
        ps.setInt(1,userID);
        ps.setInt(2,projectID);
        ps.executeUpdate();
    }
}
