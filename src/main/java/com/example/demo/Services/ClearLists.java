package com.example.demo.Services;

public class ClearLists {
    ObjectManager objectManager = new ObjectManager();

    public void clearLists(){
        objectManager.myProjectList.clear();
        objectManager.sharedProjectList.clear();
        objectManager.teamList.clear();
        objectManager.teamListIncludeCreatedBy.clear();
        objectManager.singleProjectList.clear();
        objectManager.taskList.clear();
    }
}
