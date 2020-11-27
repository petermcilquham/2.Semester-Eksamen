package com.example.demo.Repositories;

import com.example.demo.Models.Task;
import com.example.demo.Services.DBConnect;
import com.mysql.cj.protocol.Resultset;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    DBConnect connection = new DBConnect();
    List<Task> listOfTasks = new ArrayList<>();

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
                    rs.getInt(5));
            listOfTasks.add(tmp);
        }
        return listOfTasks;

    }
}
