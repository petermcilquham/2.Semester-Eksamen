package com.example.demo.Repositories;

import com.example.demo.Services.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UserRepository {
    DBConnect connection = new DBConnect();

    public void createUser(String username, String password) throws SQLException {

        PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO user (username,password) VALUES ?,?");
        ps.setString(1,username);
        ps.setString(2,password);

        ps.executeUpdate();
    }
}