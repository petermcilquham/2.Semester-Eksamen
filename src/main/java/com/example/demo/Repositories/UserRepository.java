package com.example.demo.Repositories;

import com.example.demo.Services.DBConnect;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    DBConnect connection = new DBConnect();

    //create user
    public void createUser(String username, String password) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO users (username,password) VALUES (?,?)");
        ps.setString(1,username);
        ps.setString(2,password);

        ps.executeUpdate();
    }

    //get user by created_by id
    public String getUserByID(int i) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("select distinct username from users inner join projects on userID = created_by where created_by = 1 ");
        ResultSet rs = ps.executeQuery();

        String username = "";
        if(rs.next()){
            username = rs.getString(1);
        }
        return "Project created by: " + username;
    }

    //validate user login method
    public int loginValidation(String username, String password) throws SQLException {
        PreparedStatement ps = connection.establishConnection().prepareStatement("select userID from users where username = ? and password = ?");
        ps.setString(1,username);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        int id = 0;
        if(rs.next()){
            id = rs.getInt(1);
        }
        return id;
    }

}