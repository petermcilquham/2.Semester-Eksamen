package com.example.demo.Models;

import com.example.demo.Repositories.TaskRepository;

import java.sql.SQLException;
import java.util.Date;

public class Task {
    TaskRepository taskRepository = new TaskRepository();

    private int taskID;
    private String taskName;
    private Date startDate;
    private Date endDate;
    private int taskResponsible;
    private boolean completionStatus;
    private int projectID;

    public Task(int taskID, String taskName, Date startDate, Date endDate, int taskResponsible, boolean completionStatus, int projectID) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskResponsible = taskResponsible;
        this.completionStatus = completionStatus;
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

    public int getTaskResponsible() {
        return taskResponsible;
    }
    public String getTaskResponsibleString() throws SQLException {
        return taskRepository.getTaskResponsibleRepoMethod(getTaskResponsible());
    }

    public void setTaskResponsible(int task_responsible) {
        this.taskResponsible = task_responsible;
    }

    public boolean isCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(boolean completion_status) {
        this.completionStatus = completion_status;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}
