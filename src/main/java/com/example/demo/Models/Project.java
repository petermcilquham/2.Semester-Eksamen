package com.example.demo.Models;

import com.example.demo.Repositories.UserRepository;

import java.sql.SQLException;
import java.util.Date;

public class Project {
    UserRepository userRepository = new UserRepository();

    private int projectID;
    private String projectName;
    private Date createdDate;
    private String endDate;
    private int createdBy;

    public Project(int projectID, String projectName, Date currentDay, String endDate, int createdBy) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.createdDate = currentDay;
        this.endDate = endDate;
        this.createdBy = createdBy;
    }

    public Date getCurrentDay() {
        return createdDate;
    }

    public void setCurrentDay(Date currentDay) {
        this.createdDate = currentDay;
    }

    public String getEndDate() {
        return endDate;
    }

    //returnerer noget ekstra tekst til et projekts slut dato til visning for brugeren
    public String getEndDateString() {
        return "End Date: " + endDate;
    }

    public void setEndDate(String endDate) {
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

    //returnerer noget ekstra tekst til et projekts oprettelsesdato til visning for brugeren
    public String getProjectCreatedDateString() {
        return "Start date: " + createdDate;
    }

    public Date getProjectCreatedDate() {
        return createdDate;
    }

    public void setProjectCreatedDate(Date projectCreatedDate) {
        this.createdDate = projectCreatedDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    //returnerer noget ekstra tekst til at vise den bruger der har oprettet et projekt
    public String getCreatedByString() throws SQLException {
        return "Created by: " + userRepository.getUserByID(getCreatedBy());
    }

    public void setCreatedBy(int created_by) {
        this.createdBy = created_by;
    }
}
