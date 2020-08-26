package com.daenisches_bettenlager_programming_task.SpringLoginPage.objects;

import com.daenisches_bettenlager_programming_task.SpringLoginPage.enums.LocationValidationCases;

// ValidationOutput Object that contains a validationCase enum and a location
public class ValidationOutput {

    private Location location;

    private LocationValidationCases validationCase;

    public ValidationOutput(Location location, LocationValidationCases validationCase) {
        this.location = location;
        this.validationCase = validationCase;
    }

    public ValidationOutput(LocationValidationCases validationCase) {
        this.validationCase = validationCase;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setValidationCase(LocationValidationCases validationCase) {
        this.validationCase = validationCase;
    }

    public LocationValidationCases getValidationCase() {
        return this.validationCase;
    }
}
