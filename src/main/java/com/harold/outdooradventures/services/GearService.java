package com.harold.outdooradventures.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harold.outdooradventures.models.Gear;
import com.harold.outdooradventures.models.GearCategory;
import com.harold.outdooradventures.models.Post;
import com.harold.outdooradventures.repositories.GearRepository;

@Service
public class GearService {
	@Autowired
	private GearRepository gearRepo;
	
	public List<Gear> getAllGear(){
		return gearRepo.findAll();
	}
	
	public Gear getGearById(Long id) {
		Optional<Gear> potentialGear = gearRepo.findById(id);
		if(potentialGear.isPresent()) {
			return potentialGear.get();
		}
		else {
			return null;
		}
	}
	
	public Gear createGear(String gearName, Integer rating, String review, Post post, GearCategory gearCategory) {
		Gear gear = new Gear(gearName, rating, review, post, gearCategory);
		return gearRepo.save(gear);
	}
}
