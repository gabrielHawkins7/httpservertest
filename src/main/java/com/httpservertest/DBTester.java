package com.httpservertest;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBTester {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:src/main/db/users.db";

        Scanner in = new Scanner(System.in);
        String username = in.nextLine();

        System.out.println("Username: " + username);
        System.out.println("Hashed Username: " + username.hashCode());

        Connection conn = DriverManager.getConnection(url); 
        String sql = "INSERT INTO users(id,username) VALUES(?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, username.hashCode());
        pstmt.setString(2, username);
        pstmt.executeUpdate();

        System.out.println("Inserted into DB");

    }
}
