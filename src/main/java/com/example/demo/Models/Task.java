package com.example.demo.Models;

import com.example.demo.Repositories.TaskRepository;
import com.example.demo.Services.DatesService;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

public class Task {
    DatesService datesService = new DatesService();
    TaskRepository taskRepository = new TaskRepository();

    private int taskID;
    private String taskName;
    private Date startDate;
    private Date endDate;
    private int taskResponsible;
    private boolean completionStatus;
    private int taskDurationInHours;
    private long workload;
    private int projectID;

    public Task(int taskID, String taskName, Date startDate, Date endDate, int taskResponsible, boolean completionStatus, int taskDurationInHours, long workload, int projectID) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskResponsible = taskResponsible;
        this.completionStatus = completionStatus;
        this.taskDurationInHours = taskDurationInHours;
        this.workload = workload;
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
    public String isCompletionStatusString(){
        if(isCompletionStatus()){
            return "Finished";
        }else{
            return "Unfinished";
        }
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

    public int getTaskDurationInHours() {
        return taskDurationInHours;
    }

    public void setTaskDurationInHours(int taskDurationInHours) {
        this.taskDurationInHours = taskDurationInHours;
    }

    public String getWorkload() {
        NumberFormat formatter = new DecimalFormat("#0.0");
        return formatter.format((double)getTaskDurationInHours() / (double)datesService.computeHoursPerDay(String.valueOf(getStartDate()), String.valueOf(getEndDate())));
    }

    public void setWorkload(long workload) {
        this.workload = workload;
    }
}
