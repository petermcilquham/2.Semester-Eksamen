package com.example.demo.Repositories;

import com.example.demo.Services.DBConnect;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.GregorianCalendar;

@Repository
public class TaskRepository {
    DBConnect connection = new DBConnect();

    public void createTask(String taskName, Date currentDay, String endDate, int projectID) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO tasks (task_name,start_date,end_date,projectID) VALUES (?,?,?,?)");
        ps.setString(1,taskName);
        ps.setDate(2, currentDay);
        ps.setString(3, endDate);
        ps.setInt(4, projectID);

        ps.executeUpdate();
    }

    public void deleteTask(int id) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("DELETE FROM tasks WHERE projectID = ?");
        ps.setInt(1,id);
        ps.executeUpdate();
    }
}
