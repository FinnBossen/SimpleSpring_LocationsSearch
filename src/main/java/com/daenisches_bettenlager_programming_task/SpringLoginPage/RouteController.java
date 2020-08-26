package com.daenisches_bettenlager_programming_task.SpringLoginPage;

import com.daenisches_bettenlager_programming_task.SpringLoginPage.objects.Location;
import com.daenisches_bettenlager_programming_task.SpringLoginPage.objects.ValidationOutput;
import com.daenisches_bettenlager_programming_task.SpringLoginPage.forms.LocationInputForm;
import com.daenisches_bettenlager_programming_task.SpringLoginPage.forms.LoginForm;
import com.daenisches_bettenlager_programming_task.SpringLoginPage.services.LocationValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

// basic routing Controller that handles the post and get requests of each route
@Controller
public class RouteController implements WebMvcConfigurer {

    // boolean that stores if a login occurred
    private boolean isLoggedIn = false;

    private final LocationValidatorService locationValidatorService;

    // injects locationValidationService that handles the business logic of the location validation
    @Autowired
    RouteController(LocationValidatorService locationValidatorService) {
        this.locationValidatorService = locationValidatorService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/locations")
    public String showLocations(LocationInputForm locationInputForm, Model model) {

        model.addAttribute("locations", locationValidatorService.getLocations());

        if (isLoggedIn) return "locationsForm";

        return "redirect:/login";
    }

    @PostMapping("/locations")
    public String searchLocations(@Valid LocationInputForm locationInputForm, BindingResult bindingResult, Errors errors, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) return "locationsForm";

        ValidationOutput inputValidated = locationValidatorService.validateInput(locationInputForm.getInput());

        switch (inputValidated.getValidationCase()) {
            case LocationNameError:
                redirectAttributes.addFlashAttribute("errorMessage", "LocationName: " + locationInputForm.getInput() + " is not valid");
                break;
            case PostalCodeError:
                redirectAttributes.addFlashAttribute("errorMessage", "PostalCode: " + locationInputForm.getInput() + " is not valid");
                break;
            case ParsingError:
                redirectAttributes.addFlashAttribute("errorMessage", "Error in parsing InputString " + "\"" + locationInputForm.getInput() + "\"" + " to Integer");
                break;
            case Valid:
                //adds the validLocation as a Attribute to the html template
                Location validLocation = inputValidated.getLocation();
                redirectAttributes.addFlashAttribute("validLocation", "Found valid location: \"" + validLocation.getLocationName() + " : " + validLocation.getPostalCode() + "\"");
                break;
        }

        return "redirect:/locations";

    }

    @GetMapping("/login")
    public String showLogin(LoginForm loginForm) {
        isLoggedIn = false;
        return "loginForm";
    }

    @PostMapping("/login")
    public String postLogin(@Valid LoginForm loginForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return "loginForm";

        isLoggedIn = true;
        loginForm.logUser();
        return "redirect:/locations";
    }
}
