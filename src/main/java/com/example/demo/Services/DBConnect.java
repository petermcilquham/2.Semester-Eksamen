package com.example.demo.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    public Connection establishConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/project_manager_app?serverTimezone=UTC", "root", "1");
        //standard user: root, password: 1
    }
}
