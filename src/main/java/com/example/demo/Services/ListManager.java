package com.example.demo.Services;

import com.example.demo.Models.Project;
import com.example.demo.Models.Task;

import java.util.ArrayList;
import java.util.List;

public class ListManager {
    public List<Project> myProjectList = new ArrayList<>();
    public List<Project> sharedProjectList = new ArrayList<>();
    public List<Project> singleProjectList = new ArrayList<>();
    public List<Task> listOfTasks = new ArrayList<>();

    //clear array lists
    public void clearLists(){
        myProjectList.clear();
        sharedProjectList.clear();
    }
}
