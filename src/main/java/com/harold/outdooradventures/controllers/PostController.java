package com.harold.outdooradventures.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.harold.outdooradventures.models.Activity;
import com.harold.outdooradventures.models.GearCategory;
import com.harold.outdooradventures.models.Image;
import com.harold.outdooradventures.models.Post;
import com.harold.outdooradventures.models.User;
import com.harold.outdooradventures.services.ActivityService;
import com.harold.outdooradventures.services.GearCategoryService;
import com.harold.outdooradventures.services.GearService;
import com.harold.outdooradventures.services.ImageService;
import com.harold.outdooradventures.services.PostService;
import com.harold.outdooradventures.services.UserService;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private GearService gearService;
	@Autowired
	private UserService userService;
	@Autowired
	private GearCategoryService gearCatService;
	
	@GetMapping("/new/post")
	public String newPost(
			Model model,
			HttpSession session,
			@ModelAttribute("newPost") Post newPost) {
		if(session.getAttribute("userId")==null) {
			return "redirect:/";
		}
		model.addAttribute("activities", activityService.getAllActivities());
		model.addAttribute("allGearCategories", gearCatService.getAllGearCategories());
		session.setAttribute("currentPage", null);
		return "newPost.jsp";
	}

	
	@PostMapping("/new/post")
	public String processNewPost(
			Model model,
			HttpSession session,
			@RequestParam("imageFile") MultipartFile[] imageFile,
			@RequestParam("imageDescription") String[] imageDescription,
			@RequestParam("gearName") String[] gearName,
			@RequestParam("gearCategory") String[] gearCategory,
			@RequestParam("gearRating") Integer[] gearRating,
			@RequestParam("gearReview") String[] gearReview,
			RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute("newPost") Post newPost,
			BindingResult result) throws IOException {
		
//		***********************VALIDATIONS**********************************
//		Checks requirement for number of activities to tag
		if(newPost.getActivities().size() < 1) {
			result.rejectValue("activities", "at_least_one_required", "At least one activity must be tagged");
		}
//		Checks minimum number of images requirement
		int minNumPics = 2;
//		System.out.println("# of images: " + imageFile.length);
		for(int i = 1; i <= minNumPics; i++) {
			if(imageFile[i-1].isEmpty()) {
				result.reject("image" + i + "Error", "At least two pictures must be added");
				model.addAttribute("image" + i + "Error", "At least two pictures must be added");
//				System.out.println("Not enough pictures");
			}
		}
//		Checks that each gear entry is complete or fully empty
		for(int i = 1; i <= gearName.length; i++) {
			if(!gearName[i-1].isBlank() && gearReview[i-1].isBlank()) {
				result.reject("gear" + i + "Error", "Gear entry incomplete");
				model.addAttribute("gear" + i + "Error", "Gear entry incomplete");
//				System.out.println("Gear entry incomplete");
			}
			else if(gearName[i-1].isBlank() && !gearReview[i-1].isBlank()) {
				result.reject("gear" + i + "Error", "Gear entry incomplete");
				model.addAttribute("gear" + i + "Error", "Gear entry incomplete");
//				System.out.println("Gear entry incomplete");
			}
		}
		
//		Checks that each added image has a description attached and vice-versa
		for(int i = 1; i <= imageFile.length; i++) {
			if(!imageFile[i-1].isEmpty() && imageDescription[i-1].isBlank()) {
				result.reject("image" + i + "Error", "Image and description must both be included");
				model.addAttribute("image" + i + "Error", "Image and description must both be included");
//				System.out.println("Picture missing");
			}
			else if(imageFile[i-1].isEmpty() && !imageDescription[i-1].isBlank()) {
				result.reject("image" + i + "Error", "Image and description must both be included");
				model.addAttribute("image" + i + "Error", "Image and description must both be included");
//				System.out.println("Description missing");
			}
		}

		if(result.hasErrors()) {
			model.addAttribute("allGearCategories", gearCatService.getAllGearCategories());
			model.addAttribute("activities", activityService.getAllActivities());
			return "newPost.jsp";
		}
//		***********************VALIDATIONS**********************************		

		Post savedPost = postService.createPost(newPost);
		Long postId = savedPost.getId();
		int counter = 0;
		for(MultipartFile file : imageFile) {
			if (file.isEmpty()) {
				counter++;
				continue;
			}
			String identifier = postService.saveFile(file);
			imageService.createImage(identifier, imageDescription[counter], savedPost);
			counter++;
//			System.out.println(counter);
		}
		
		
		for(int i = 0; i < gearName.length; i++) {
			if(gearName[i].isBlank() && gearReview[i].isBlank()) {
				continue;
			}
			Long gearCatId = Long.valueOf(gearCategory[i]).longValue();
			
			gearService.createGear(gearName[i], gearRating[i], gearReview[i], savedPost, gearCatService.getGearCategoryById(gearCatId));
		}

//		Redirects to the new post
		return "redirect:/posts/view/" + postId;
	}
	

//	View posts with a certain tagged activity
	@GetMapping("/posts/{activityName}/{activityId}")
	public String showPostsByActivity(
			@PathVariable("activityName") String activityName,
			@PathVariable("activityId") Long activityId,
			Model model,
			HttpSession session) {
		Activity chosenActivity = activityService.getActivityById(activityId);
		model.addAttribute("chosenActivity", chosenActivity);
		List<Post> allPosts = postService.getAllByActivity(chosenActivity);
//		System.out.println(allPosts);
		for(Post post : allPosts) {
			List<Image> images = post.getImages();
			images.get(0).setIdentifier(postService.generatePictureUrl(images.get(0).getIdentifier()));		
		}
		model.addAttribute("allPosts", allPosts);
		session.setAttribute("currentPage", "/posts/" + activityName + "/" + activityId);
		return "showPostsByActivity.jsp";
	}
	
//	View Posts by User
	@GetMapping("/posts/user/{id}")
	public String showPostsByUser(
			@PathVariable("id") Long id,
			Model model,
			HttpSession session) {
		User user = userService.findUserById(id);
		
		model.addAttribute("user", user);
		List<Post> allPosts = postService.getAllByUser(user);
		for(Post post : allPosts) {
			List<Image> images = post.getImages();
			images.get(0).setIdentifier(postService.generatePictureUrl(images.get(0).getIdentifier()));		
		}
		model.addAttribute("allPosts", allPosts);
		session.setAttribute("currentPage", "/posts/user/" + id);
		return "viewPostsByUser.jsp";
	}
	
//	View post by tagged gear categories
	@GetMapping("/posts/gear/{gearCategoryName}/{gearCategoryId}")
	public String showPostsByGearCategory(
			Model model,
			@PathVariable("gearCategoryName") String gearCategoryName,
			@PathVariable("gearCategoryId") Long gearCategoryId) {
		GearCategory gearCat = gearCatService.getGearCategoryById(gearCategoryId);
		List<Post> allPosts = postService.getPostsByGearCategory(gearCat);
		for(Post post : allPosts) {
			List<Image> images = post.getImages();
			images.get(0).setIdentifier(postService.generatePictureUrl(images.get(0).getIdentifier()));		
		}
		model.addAttribute("allPosts",allPosts);
		model.addAttribute("chosenCategory", gearCat);
		return "viewPostsByGearCat.jsp";
	}	
	
//	View a single Post
	@GetMapping("/posts/view/{postId}")
	public String viewPost(
			@PathVariable("postId") Long postId,
			Model model,
			HttpSession session) {
		Post selectedPost = postService.getPostById(postId);
		for(Image image : selectedPost.getImages()) {
			image.setIdentifier(postService.generatePictureUrl(image.getIdentifier()));
		}
		model.addAttribute("post", selectedPost);
		session.setAttribute("currentPage", "/posts/view/" + postId);
		return "viewPost.jsp";
	}
	

}




























