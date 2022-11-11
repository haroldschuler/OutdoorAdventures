package com.harold.outdooradventures.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.harold.outdooradventures.services.ActivityService;
import com.harold.outdooradventures.services.GearCategoryService;

@Controller
public class MainController {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private GearCategoryService gearCatService;
	
//	Home Page
	@GetMapping("/")
	public String home(
			Model model,
			HttpSession session) {
		int limit = 12;
		
		model.addAttribute("allActivities", activityService.getActivitiesLimitNumber(limit));
		session.setAttribute("currentPage", "/");
		return "home.jsp";
	}
	
	
//	Displays the list of gear categories
	@GetMapping("/gear")
	public String searchGear(Model model, HttpSession session) {
		session.setAttribute("currentPage", "/");
		model.addAttribute("allGearCategories",gearCatService.getAllGearCategories());
		return "searchGearCategories.jsp";
	}
}
