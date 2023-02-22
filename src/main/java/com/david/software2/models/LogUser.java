package com.david.software2.models;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
/**
 * The LogUser class for the login activity.
 * This class will be called when a user logs in.
 * When a user logs in it will write to the login_activity.txt file.
 */
public  class LogUser {
    //class that will be called when users login
    //when a user logs in, a new instance of this class will be created
    //the instance variables will be logged to a file in the root directory
    //the file will be called login_activity.txt


    /**
     * Constructer for the LogUser class
     */
    public LogUser(String username, LocalDateTime loginAttemptTime, boolean loginSuccess) throws IOException {
        String stringToWrite = "Username: " + username + " Login Attempt Time: " + loginAttemptTime + " Login Success: " + loginSuccess + "\n";
        FileWriter fw = new FileWriter("login_activity.txt", true);
        fw.write(stringToWrite);
        fw.flush();
        fw.close();
    }
}


