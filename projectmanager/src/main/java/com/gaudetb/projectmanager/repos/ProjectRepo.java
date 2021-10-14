package com.gaudetb.projectmanager.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gaudetb.projectmanager.models.Project;


@Repository
public interface ProjectRepo extends CrudRepository<Project, Long> {
	List<Project> findAll();
}
