package com.harold.outdooradventures.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harold.outdooradventures.models.Activity;
import com.harold.outdooradventures.repositories.ActivityRepository;

@Service
public class ActivityService {
	@Autowired
	private ActivityRepository activityRepo;
	
	public List<Activity> getAllActivities(){
		return activityRepo.findAll();
	}
	
	public List<Activity> getActivitiesLimitNumber(int limit){
		List<Activity> activities = activityRepo.findAll();
		List<Activity> limitedList = new ArrayList<Activity>();
		if(limit > activities.size()) {
			limit = activities.size();
		}
		for(int i = 0; i < limit; i++) {
			limitedList.add(activities.get(i));
			
		}
		return limitedList;
	}
	
	public Activity getActivityById(Long id) {
		Optional<Activity> potentialActivity = activityRepo.findById(id);
		if(potentialActivity.isPresent()) {
			return potentialActivity.get();
		}
		else {
			return null;
		}
	}
}
