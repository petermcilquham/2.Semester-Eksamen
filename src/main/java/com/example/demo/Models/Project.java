package com.example.demo.Models;

import com.example.demo.Repositories.UserRepository;

import java.sql.SQLException;
import java.util.Date;

public class Project {
    UserRepository userRepository = new UserRepository();

    private int projectID;
    private String projectName;
    private Date currentDay;
    private Date endDate;
    private int createdBy;

    public Project(int projectID, String projectName, Date currentDay, Date endDate, int createdBy) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.currentDay = currentDay;
        this.endDate = endDate;
        this.createdBy = createdBy;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getProjectCreatedDate() {
        return currentDay;
    }

    public void setProjectCreatedDate(Date projectCreatedDate) {
        this.currentDay = projectCreatedDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public String getCreatedByString() throws SQLException {
        return "Created by: " + userRepository.getUserByID(getCreatedBy());
    }

    public void setCreatedBy(int created_by) {
        this.createdBy = created_by;
    }
}
