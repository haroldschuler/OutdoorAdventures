package com.harold.outdooradventures.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harold.outdooradventures.models.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
	List<Image> findAll();
}
