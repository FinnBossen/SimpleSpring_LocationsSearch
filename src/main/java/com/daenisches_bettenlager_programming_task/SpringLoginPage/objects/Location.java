package com.daenisches_bettenlager_programming_task.SpringLoginPage.objects;

// location Object that stores the locationName and the postalCode
public class Location {

    private String locationName;

    private int postalCode;

    public Location(String locationName, int postalCode) {
        this.locationName = locationName;
        this.postalCode = postalCode;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public int getPostalCode() {
        return this.postalCode;
    }
}
