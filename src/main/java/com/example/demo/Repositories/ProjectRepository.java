package com.example.demo.Repositories;

import com.example.demo.Services.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

@Repository
public class ProjectRepository {
    DBConnect connection = new DBConnect();

    public void createProject(String projectName, Date createdDate, int createdBy) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO projects (project_name, project_created_date, created_by) VALUES (?,?,?)");
        ps.setString(1,projectName);
        ps.setDate(2,createdDate);
        ps.setInt(3,createdBy);

        ps.executeUpdate();
    }

    public void deleteProject(int id) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("DELETE FROM projects WHERE id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
    }
}
