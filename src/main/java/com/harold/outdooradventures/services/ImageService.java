package com.harold.outdooradventures.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harold.outdooradventures.models.Image;
import com.harold.outdooradventures.models.Post;
import com.harold.outdooradventures.repositories.ImageRepository;

@Service
public class ImageService {
	@Autowired
	private ImageRepository imageRepo;
	
	public Image getImageById(Long id) {
		Optional<Image> potentialImage = imageRepo.findById(id);
		if(potentialImage.isPresent()) {
			return potentialImage.get();
		}
		else {
			return null;
		}
	}
	
	public Image createImage(String identifier, String description, Post post) {
		Image image = new Image(identifier, description, post);
		return imageRepo.save(image);
	}
		
	
}
