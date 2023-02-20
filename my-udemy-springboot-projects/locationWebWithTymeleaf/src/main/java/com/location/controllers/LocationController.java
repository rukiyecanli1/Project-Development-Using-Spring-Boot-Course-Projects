package com.location.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.location.entities.Location;
import com.location.repo.LocationRepository;
import com.location.service.LocationService;
import com.location.util.EmailUtil;
import com.location.util.ReportUtil;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@Controller
public class LocationController implements ErrorController{

	@Autowired
	LocationService service;
	
	@Autowired
	LocationRepository repo;

	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	ReportUtil reportUtil;
	
	@Autowired
	ServletContext sc;
	
	// this method returns a string which is a JSP page to display to the end user
	// if the user hits this particular URL("../showCreate") through our
	// application, a page which is createLocation.jsp will be sent to him.
	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation"; // the JSP page name
	}

	// if the user clicks "save" button through our application, this uri will open
	// and then locationwill be saven in db and success message will be sent to
	// the createLocation.jsp to show it to the user
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		Location locationSaved = service.saveLocation(location);
		// Hibernate: select l1_0.id,l1_0.code,l1_0.name,l1_0.type from location l1_0
		// where l1_0.id=?
		// Hibernate: update location set code=?, name=?, type=? where id=?
		String successMsg = "Location saved with id: " + locationSaved.getId();
		modelMap.addAttribute("successMsg", successMsg);
		
		//will send email to sprigxyzabc@gmail.com when the user save a location
		emailUtil.sendEmailString("springxyzabc@gmail.com", "Location saved", 
	         "Location saved successfully and about to return a response");
		
		return "createLocation";
	}

	// if the user clicks "view all" button through our application, this uri will
	// open and then locations will be sent to displayLocation.jsp to show them as a
	// table to the user
	@RequestMapping("/displayLocs")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

	// if the user clicks "delete" button through our application, this uri will
	// open and then choosen location will be deleted and new locations list will be sent
	// to displayLocation.jsp to show them as a new table to the user
	@RequestMapping("/deleteLoc")
	public String deleteLocation(@RequestParam("id") int locId, ModelMap modelMap) {
		// Location location = service.getLocationById(locId);
		Location location = new Location();
		location.setId(locId);
		service.deleteLocation(location);
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

	// if the user clicks "edit" button through our application, this uri will
	// open and then the choosen one will be added a model and edit page wil be open
	@RequestMapping("/showEdit")
	public String showEdit(@RequestParam("id") int locId, ModelMap modelMap) {
		Location location = service.getLocationById(locId);
		modelMap.addAttribute("location", location);
		return "editLocation";
	}
    
	// when the user fill the form again and save the button, this uri will open
	// and updated locations will be sent to displayLocations page to show it to user
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		service.updateLocation(location);
		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("/generateReport")
	public String generateReport() {
		String path = sc.getRealPath("/");
		List<Object[]> data = repo.findByRunId();
		reportUtil.generatePieChart(path, data);
		return "report";
	}
	
	 @RequestMapping("/error")
	    public ModelAndView handleError(HttpServletResponse response) {
	        int status = response.getStatus();
	        if ( status == HttpStatus.NOT_FOUND.value()) {
	            System.out.println("Error with code " + status + " Happened!");
	            return new ModelAndView("error-404");
	        } else if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            System.out.println("Error with code " + status + " Happened!");
	            return new ModelAndView("error-500");
	        }
	        System.out.println(status);
	        return new ModelAndView("error");
	    }
}
