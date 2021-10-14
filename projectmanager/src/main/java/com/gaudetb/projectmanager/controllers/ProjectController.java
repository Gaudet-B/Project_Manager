package com.gaudetb.projectmanager.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.gaudetb.projectmanager.models.User;
import com.gaudetb.projectmanager.models.Project;
import com.gaudetb.projectmanager.services.ProjectService;
import com.gaudetb.projectmanager.services.UserService;

@Controller
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userService;

	// ============> DISPLAY ROUTES <============
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		
		if (session.getAttribute("uuid") == null) return "redirect:/bookclub";
		
		model.addAttribute("allProjects", projectService.findAll());
//		model.addAttribute("yourProjects", userService.findOne());
		model.addAttribute("loggedInUser", userService.findOne((Long) session.getAttribute("uuid")));
		
		return "dashboard.jsp";
	}
	
	@GetMapping("/new")
	public String newProject(@ModelAttribute("newProject") Project project, HttpSession session) {
		
		if (session.getAttribute("uuid") == null) return "redirect:/";
		
		return "create.jsp";
	}
	
	@GetMapping("/projects/{id}")
	public String viewBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if (session.getAttribute("uuid") == null) return "redirect:/";
		
		model.addAttribute("loggedInUser", userService.findOne((Long) session.getAttribute("uuid")));
		model.addAttribute("project", projectService.findOne(id));
		
		return "view.jsp";
	}
	
	@GetMapping("/projects/{id}/edit")
	public String editBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if (session.getAttribute("uuid") == null) return "redirect:/";
		
		Project project = projectService.findOne(id);
		
		if (!session.getAttribute("uuid").equals(project.getLead().getId())) return "redirect:/dashboard";
		
		model.addAttribute("loggedInUser", userService.findOne((Long) session.getAttribute("uuid")));
		model.addAttribute("project", project);
		
		return "edit.jsp";
	}
	
	@GetMapping("projects/{id}/tasks")
	public String viewTasks(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if (session.getAttribute("uuid") == null) return "redirect:/";
		
		Project project = projectService.findOne(id);
		
		model.addAttribute("loggedInUser", userService.findOne((Long) session.getAttribute("uuid")));
		model.addAttribute("project", project);
		
		return "tasks.jsp";
	}

	// ============> ACTION ROUTES <============
	
	@PostMapping("/projects/create")
	public String createProject(@Valid @ModelAttribute("newProject") Project project, BindingResult result, HttpSession session) {
		
		if (session.getAttribute("uuid") == null) return "redirect:/";
		
		if (result.hasErrors()) return "create.jsp";
		
		User user = userService.findOne((Long) session.getAttribute("uuid"));
		project.setLead(user);
//		project.getUsers().add(user);
		
		Project p = projectService.save(project);
		System.out.println(p);
//		projectService.addUser(p.getId(), user.getId());
		
		return "redirect:/dashboard";
	}
	
	@PutMapping("/projects/{id}/update")
	public String updateProject(@Valid @ModelAttribute("book") Project projectFromForm, BindingResult result, HttpSession session, @PathVariable("id") Long id, Model model) {
		
		if (session.getAttribute("uuid") == null) return "redirect:/";
		
		Project projectFromDB = projectService.findOne(id);
		User loggedInUser = userService.findOne((Long) session.getAttribute("uuid"));
		
		if (result.hasErrors()) {
			model.addAttribute("loggedInUser", loggedInUser);
//			model.addAttribute("project", projectFromDB);
			return "edit.jsp";
		} else {
			if (!session.getAttribute("uuid").equals(projectFromDB.getLead().getId())) return "redirect:/dashboard";
			// safe update:
			projectFromDB.setTitle(projectFromForm.getTitle());
			projectFromDB.setDescription(projectFromForm.getDescription());
			projectFromDB.setDueDate(projectFromForm.getDueDate());
			// add loggedInUser to users List:
			projectFromDB.getUsers().add(loggedInUser);
			
			projectService.save(projectFromDB);
		}
		
		return"redirect:/dashboard";
	}
	
	@GetMapping("/projects/{id}/join")
	public String joinTeam(@PathVariable("id") Long id, HttpSession session) {
		
		Long userId = (Long) session.getAttribute("uuid");
		
		projectService.addUser(id, userId);
		
		return "redirect:/dashboard";
	}
	
	@GetMapping("/projects/{id}/leave")
	public String leaveTeam(@PathVariable("id") Long id, HttpSession session) {
		
		Long userId = (Long) session.getAttribute("uuid");
		
		projectService.removeUser(id, userId);
		
		return "redirect:/dashboard";
	}
		
	@DeleteMapping("/projects/{id}/delete")
	public String deleteProject(@PathVariable("id") Long id, HttpSession session) {
		
		if (session.getAttribute("uuid") == null) return "redirect:/";
		
		Project project = projectService.findOne(id);
		
		// check to make sure the loggedInUser is the User who created the Project (the project's lead):
		if (!session.getAttribute("uuid").equals(project.getLead().getId())) return "redirect:/dashboard";
		
		projectService.delete(id);
		
		return "redirect:/bookclub/dashboard";
	}

}
