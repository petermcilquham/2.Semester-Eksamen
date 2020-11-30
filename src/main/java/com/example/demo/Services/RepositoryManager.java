package com.example.demo.Services;

import com.example.demo.Repositories.ProjectRepository;
import com.example.demo.Repositories.TaskRepository;
import com.example.demo.Repositories.UserRepository;

public class RepositoryManager {
    public ProjectRepository pRep = new ProjectRepository();
    public TaskRepository tRep = new TaskRepository();
    public UserRepository uRep = new UserRepository();
}
