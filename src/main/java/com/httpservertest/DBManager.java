package com.httpservertest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBManager {
    private String url = "jdbc:sqlite:src/main/db/users.db";

    
    public Map<Integer, String> getUsers(){
        HashMap<Integer, String> users = new HashMap<>();
        try{
            Connection conn = DriverManager.getConnection(url);
            String sql = "SELECT id, username FROM users";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                users.put(rs.getInt("id"), rs.getString("username"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return users;
    }

}
