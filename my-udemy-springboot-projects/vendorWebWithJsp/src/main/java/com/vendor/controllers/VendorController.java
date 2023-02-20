package com.vendor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.vendor.entities.Vendor;

import com.vendor.service.VendorService;

@Controller
public class VendorController {

	@Autowired
	VendorService service;

	// this method returns a string which is a JSP page to display to the end user
	// if the user hits this particular URL("../showCreate") through our
	// application, a page which is createvendor.jsp will be sent to him.
	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createVendor"; // the JSP page name
	}

	// if the user clicks "save" button through our application, this uri will open
	// and then vendor will be saven in db and success message will be sent to
	// the createVnedor.jsp to show it to the user
	@RequestMapping("/saveVendor")
	public String saveVendor(@ModelAttribute("vendor") Vendor vendor, ModelMap modelMap) {
		Vendor vendorSaved = service.saveVendor(vendor);
		// Hibernate: select l1_0.id,l1_0.code,l1_0.name,l1_0.type from vendor l1_0
		// where l1_0.id=?
		// Hibernate: update vendor set code=?, name=?, type=? where id=?
		String successMsg = "Vendor saved with id: " + vendorSaved.getId();
		modelMap.addAttribute("successMsg", successMsg);
		return "createVendor";
	}
	
	// if the user clicks "view all" button through our application, this uri will
		// open and then vendors will be sent to displayVendor.jsp to show them as a
		// table to the user
		@RequestMapping("/displayVendors")
		public String displayVendors(ModelMap modelMap) {
			List<Vendor> vendors = service.getAllVendors();
			modelMap.addAttribute("vendors", vendors);
			return "displayVendors";
		}
		
		// if the user clicks "delete" button through our application, this uri will
		// open and then choosen vendor will be deleted and new vendors list will be sent
		// to displayVendor.jsp to show them as a new table to the user
		@RequestMapping("/deleteVendor")
		public String deleteVendor(@RequestParam("id") int venId, ModelMap modelMap) {
			// vendor vendor = service.getvendorById(locId);
			Vendor vendor = new Vendor();
			vendor.setId(venId);
			service.deleteVendor(vendor);
			List<Vendor> vendors = service.getAllVendors();
			modelMap.addAttribute("vendors", vendors);
			return "displayVendors";
		}
		
		// if the user clicks "edit" button through our application, this uri will
		// open and then the choosen one will be added a model and edit page wil be open
		@RequestMapping("/showEdit")
		public String showEdit(@RequestParam("id") int locId, ModelMap modelMap) {
			Vendor vendor = service.getVendorById(locId);
			modelMap.addAttribute("vendor", vendor);
			return "editVendor";
		}

		// when the user fill the form again and click save the button, this uri will open
		// and updated vendors will be sent to displayVendors page to show it to user
		@RequestMapping("/updateVendor")
		public String updateVendor(@ModelAttribute("vendor") Vendor vendor, ModelMap modelMap) {
			service.updateVendor(vendor);
			List<Vendor> vendors = service.getAllVendors();
			modelMap.addAttribute("vendors", vendors);
			return "displayVendors";
		}
}
