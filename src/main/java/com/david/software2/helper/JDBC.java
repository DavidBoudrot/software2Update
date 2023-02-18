package com.david.software2.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class JDBC {



private static final String protocol = "jdbc";
private static final String vendor = ":mysql:";
private static final String location = "//localhost:";

private static final String port = "3306/";
private static final String databaseName = "Appointments";
private static final String jdbcUrl = protocol + vendor + location + port + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
private static final String userName = "david"; // Username
private static String password = "4141"; // Password
public static Connection connection;  // Connection Interface

public static void openConnection()
        {
        try {
        Class.forName(driver); // Locate Driver
        connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
        System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
        System.out.println("Error:" + e.getMessage());
                System.out.println("Current URL: " + jdbcUrl);
        }
        }

public static void closeConnection() {
        try {
        connection.close();
        System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
        System.out.println("Error:" + e.getMessage());
        }
        }
        public static Connection getConnection() {
        return connection;
        }


        }




