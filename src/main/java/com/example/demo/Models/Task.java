package com.example.demo.Models;

import java.util.Date;

public class Task {

    private int taskID;
    private String taskName;
    private Date currentDay;
    private Date endDate;

    public Task(int taskID, String taskName, Date currentDay, Date endDate) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.currentDay = currentDay;
        this.endDate = endDate;
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
        return currentDay;
    }

    public void setStartDate(Date startDate) {
        this.currentDay = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
