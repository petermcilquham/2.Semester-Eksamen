package com.example.demo.Services;

import com.example.demo.Models.Project;
import com.example.demo.Models.Task;
import com.example.demo.Models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClearListsTest {

    //her tester vi om vores clearlist metode virker
    @Test
    void clearLists() throws SQLException {
        ObjectManager objectManager = new ObjectManager();

        //vælger tre forskellige lister myprojectlist, teamList og tasklist
        objectManager.pRep.getMyProjects(1);
        objectManager.uRep.getTeamList(1);
        objectManager.tRep.getTaskList(1);

        objectManager.clearLists();

        assertEquals(0,objectManager.myProjectList.size());
        assertEquals(0,objectManager.teamList.size());
        assertEquals(0,objectManager.taskList.size());
    }
}