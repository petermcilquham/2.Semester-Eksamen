package com.example.demo.Services;

public class CompareDates {
    public boolean compareDates(String projectDate, String taskDate){
        int date1 = Integer.parseInt(projectDate);
        int date2 = Integer.parseInt(taskDate);
        return date1 > date2;
    }
}
