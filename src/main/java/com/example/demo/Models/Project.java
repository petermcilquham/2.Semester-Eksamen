package com.example.demo.Models;

import java.util.Date;

public class Project {

    private int projectID;
    private String projectName;
    private Date projectCreatedDate;

    public Project(int projectID, String projectName, Date projectCreatedDate) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectCreatedDate = projectCreatedDate;
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
        return projectCreatedDate;
    }

    public void setProjectCreatedDate(Date projectCreatedDate) {
        this.projectCreatedDate = projectCreatedDate;
    }
}
