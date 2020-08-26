package com.daenisches_bettenlager_programming_task.SpringLoginPage.services;

import com.daenisches_bettenlager_programming_task.SpringLoginPage.SpringLoginPageApplication;
import com.daenisches_bettenlager_programming_task.SpringLoginPage.objects.Location;
import com.daenisches_bettenlager_programming_task.SpringLoginPage.objects.ValidationOutput;
import com.daenisches_bettenlager_programming_task.SpringLoginPage.enums.LocationValidationCases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// location validation Service that handles the business logic of searching the stored locations
@Service
public class LocationValidatorService {

    private static final Logger logger = LogManager.getLogger(SpringLoginPageApplication.class);

    // list that stores some example locations to validate
    private final List<Location> locations = new ArrayList<>(Arrays.asList(
            new Location("Handewitt", 24976),
            new Location("Flensburg", 24937),
            new Location("Süderlügum", 25923),
            new Location("Kiel", 24103),
            new Location("Hamburg", 20095),
            new Location("Eckernförde", 24340),
            new Location("Westerland", 25980),
            new Location("Niebüll", 25899)));

    // validates the String input of the user
    // returns the type ValidationOutput that contains a enum
    // which describes the validationCase and a location if it found one
    public ValidationOutput validateInput(String input) {
        //checks if input String contains only numbers, when yes it assumes that it's a PostalCode
        if (input.matches("[0-9]+")) {
            try {
                int integerPostalCode = Integer.parseInt(input);
                return checkLocations(integerPostalCode);
            } catch (NumberFormatException e) {
                logger.error("Error in Parsing String to Integer", e);
                return new ValidationOutput(LocationValidationCases.ParsingError);
            }
        }
        return checkLocations(input);
    }

    // returns all stored locations
    public List<Location> getLocations() {
        return locations;
    }

    //checks if postalCode is present in the locations list
    private ValidationOutput checkLocations(int postalCode) {

        Location foundLocation = locations.stream().filter(l -> l.getPostalCode() == postalCode).findFirst().orElse(null);
        if (foundLocation != null) {
            return new ValidationOutput(foundLocation, LocationValidationCases.Valid);
        }
        return new ValidationOutput(LocationValidationCases.PostalCodeError);
    }

    //checks if postalCode is present in the locations list
    private ValidationOutput checkLocations(String locationName) {

        Location foundLocation = locations.stream().filter(l -> l.getLocationName().equals(locationName)).findFirst().orElse(null);
        if (foundLocation != null) {
            return new ValidationOutput(foundLocation, LocationValidationCases.Valid);
        }
        return new ValidationOutput(LocationValidationCases.LocationNameError);
    }
}
