package com.daenisches_bettenlager_programming_task.SpringLoginPage.forms;

import com.daenisches_bettenlager_programming_task.SpringLoginPage.SpringLoginPageApplication;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.constraints.*;

// login form for the loginForm template it validates the user given input with validation beans
// and has a function to log the user with log4j
public class LoginForm {

    // logger from log4j
    private static final Logger logger = LogManager.getLogger(SpringLoginPageApplication.class);

    // the custom log level that I created to separate User logs from other logs
    final Level logUser = Level.forName("LOGUSER", 99);

    // checks if input from user is not null or empty
    @NotNull
    @NotEmpty
    private String userID;

    @NotNull
    @NotEmpty
    private String password;

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void logUser() {
        logger.log(logUser, this.userID + " logged in with password " + this.password);
    }
}
