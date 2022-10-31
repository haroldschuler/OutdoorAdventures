package com.harold.outdooradventures.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harold.outdooradventures.models.GearCategory;

@Repository
public interface GearCategoryRepository extends CrudRepository<GearCategory, Long> {
	List<GearCategory> findAll();
}
