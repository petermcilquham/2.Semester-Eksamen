package com.example.demo.Models;

import java.util.Date;

public class Task {

    private int taskID;
    private String taskName;
    private Date startDate;
    private Date endDate;
    private int projectID;

    public Task(int taskID, String taskName, Date startDate, Date endDate, int projectID) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectID = projectID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
