package com.example.demo.Services;

import com.example.demo.Models.Project;
import com.example.demo.Models.Task;
import com.example.demo.Models.User;
import com.example.demo.Repositories.ProjectRepository;
import com.example.demo.Repositories.TaskRepository;
import com.example.demo.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class ObjectManager {
    //repositories
    public ProjectRepository pRep = new ProjectRepository();
    public TaskRepository tRep = new TaskRepository();
    public UserRepository uRep = new UserRepository();

    //models
    public Project project = new Project(0,"",null,0);
    public Task task = new Task(0,"",null,null,0, false, 0);
    public User user = new User(0,"","");

    //lists
    public List<Project> myProjectList = new ArrayList<>();
    public List<Project> sharedProjectList = new ArrayList<>();
    public List<Project> singleProjectList = new ArrayList<>();
    public List<Task> taskList = new ArrayList<>();
    public List<User> teamList = new ArrayList<>();
    public List<User> teamListIncludeCreatedBy = new ArrayList<>();

    //metode til at slette alle lister
    public void clearLists(){
        myProjectList.clear();
        sharedProjectList.clear();
        teamList.clear();
        teamListIncludeCreatedBy.clear();
        singleProjectList.clear();
        taskList.clear();
    }

}
