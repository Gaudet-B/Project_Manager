package com.gaudetb.projectmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaudetb.projectmanager.models.Project;
import com.gaudetb.projectmanager.models.User;
import com.gaudetb.projectmanager.repos.ProjectRepo;


@Service
public class ProjectService {
	
	@Autowired
	ProjectRepo projectRepo;
	
	@Autowired
	UserService userService;

	// ============> CREATE / UPDATE <============
	
	public Project save(Project project) {
		return projectRepo.save(project);
	}
	
	public Project addUser(Long projectId, Long userId) {
		Project project = findOne(projectId);
		User user = userService.findOne(userId);
//		System.out.println(project.getUsers());
		project.getUsers().add(user);
		return save(project);
	}
	
	public Project removeUser(Long projectId, Long userId) {
		Project project = findOne(projectId);
		User user = userService.findOne(userId);
//		System.out.println(project.getUsers());
		project.getUsers().remove(user);
		return save(project);
	}

	// ============> READ <============
	
	public List<Project> findAll() {
		return projectRepo.findAll();
	}
	
	public Project findOne(Long id) {
		Optional<Project> optionalProject = projectRepo.findById(id);
		if (optionalProject.isPresent()) return optionalProject.get();
		else return null;
	}
	

	// ============> DELETE <============

	public void delete(Long id) {
		projectRepo.deleteById(id);
	}

}
