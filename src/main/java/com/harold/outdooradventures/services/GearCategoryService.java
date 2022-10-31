package com.harold.outdooradventures.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harold.outdooradventures.models.GearCategory;
import com.harold.outdooradventures.repositories.GearCategoryRepository;

@Service
public class GearCategoryService {
	@Autowired
	private GearCategoryRepository gearCatRepo;
	
	public List<GearCategory> getAllGearCategories(){
		return gearCatRepo.findAll();
	}
	
	public GearCategory getGearCategoryById(Long id) {
		Optional<GearCategory> potentialGearCat = gearCatRepo.findById(id);
		if(potentialGearCat.isPresent()) {
			return potentialGearCat.get();
		}
		else {
			return null;
		}
	}
}
