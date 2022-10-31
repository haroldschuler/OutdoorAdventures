package com.harold.outdooradventures.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harold.outdooradventures.models.Activity;
import com.harold.outdooradventures.models.GearCategory;
import com.harold.outdooradventures.models.Post;
import com.harold.outdooradventures.models.User;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	List<Post> findAll();
	
	List<Post> findAllByActivities(Activity activity);
	
	List<Post> findAllByUser(User user);
	
	List<Post> findPostsByGear_GearCategory(GearCategory gearCategory);
	
}
