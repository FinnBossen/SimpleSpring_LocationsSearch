package com.daenisches_bettenlager_programming_task.SpringLoginPage.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

// the input Form of the locationsSearch template
// validation of the user given input with validation beans
public class LocationInputForm {

    // checks if input from the user is not null or empty
    @NotNull
    @NotEmpty
    private String input;

    public String getInput() {

        return this.input;
    }

    public void setInput(String input) {

        this.input = input;
    }

}
