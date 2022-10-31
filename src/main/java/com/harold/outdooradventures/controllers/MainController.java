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
	
	@GetMapping("/")
	public String home(
			Model model,
			HttpSession session) {
		int limit = 12;
		
		model.addAttribute("allActivities", activityService.getActivitiesLimitNumber(limit));
		session.setAttribute("currentPage", "/");
		return "home.jsp";
	}
	
	
//	****************WORK ON THIS TO ADD A LIST OF ALL GEAR CATEGORIES**************
	@GetMapping("/gear")
	public String searchGear(Model model, HttpSession session) {
		session.setAttribute("currentPage", "/");
		model.addAttribute("allGearCategories",gearCatService.getAllGearCategories());
		return "searchGearCategories.jsp";
	}
	
//	Can use this by adding a button to test functions
//	@PostMapping("/test")
//	public String test(
//			HttpSession session) {
//		
//		System.out.println(session.getAttribute("userId"));
//		return "redirect:/";
//	}
}
