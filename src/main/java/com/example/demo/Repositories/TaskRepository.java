package com.example.demo.Repositories;

import com.example.demo.Models.Task;
import com.example.demo.Services.DBConnect;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    DBConnect connection = new DBConnect();
    List<Task> listOfTasks = new ArrayList<>();

    public void createTask(String taskName, Date currentDay, String endDate, int taskResponsible, int projectID) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO tasks (task_name,start_date,end_date,task_responsible,projectID) VALUES (?,?,?,?,?)");
        ps.setString(1,taskName);
        ps.setDate(2, currentDay);
        ps.setString(3, endDate);
        ps.setInt(4,taskResponsible);
        ps.setInt(5, projectID);

        ps.executeUpdate();
    }

    public void deleteTask(int id) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("DELETE FROM tasks WHERE projectID = ?");
        ps.setInt(1,id);
        ps.executeUpdate();
    }
    public List<Task> getTaskList(int projectID) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT * from tasks where projectID = ? ORDER BY start_date ASC");
        ps.setInt(1,projectID);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Task tmp = new Task(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDate(3),
                    rs.getDate(4),
                    rs.getInt(5),
                    rs.getBoolean(6),
                    rs.getInt(7));
            listOfTasks.add(tmp);
        }
        return listOfTasks;
    }

    public void editTask(String taskName, String startDate, String endDate, int taskResponsible, boolean completionStatus, int taskID) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("UPDATE tasks set task_name = ?, start_date = ?, end_date = ?, task_responsible = ?, completion_status = ? where taskID = ?");
        ps.setString(1,taskName);
        ps.setString(2,startDate);
        ps.setString(3,endDate);
        ps.setInt(4,taskResponsible);
        ps.setBoolean(5,completionStatus);
        ps.setInt(6,taskID);

        ps.executeUpdate();
    }
}
