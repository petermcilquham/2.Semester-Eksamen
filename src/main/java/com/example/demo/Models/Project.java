package com.example.demo.Models;

import java.util.Date;

public class Project {

    private int projectID;
    private String projectName;
    private Date projectCreatedDate;
    private int createdBy;

    public Project(int projectID, String projectName, Date projectCreatedDate, int createdBy) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectCreatedDate = projectCreatedDate;
        this.createdBy = createdBy;
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

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int created_by) {
        this.createdBy = created_by;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectID=" + projectID +
                ", projectName='" + projectName + '\'' +
                ", projectCreatedDate=" + projectCreatedDate +
                ", created_by=" + createdBy +
                '}';
    }
}
