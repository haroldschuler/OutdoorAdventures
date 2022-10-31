package com.harold.outdooradventures.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harold.outdooradventures.models.Gear;

@Repository
public interface GearRepository extends CrudRepository<Gear, Long> {
	List<Gear> findAll();
}
