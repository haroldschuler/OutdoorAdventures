package com.harold.outdooradventures.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harold.outdooradventures.models.Activity;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {
	List<Activity> findAll();
}
