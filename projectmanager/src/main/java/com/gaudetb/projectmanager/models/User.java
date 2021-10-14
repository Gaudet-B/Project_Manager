package com.gaudetb.projectmanager.models;

import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {

	// ============> PRIMARY KEY <============
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// ============> MEMBER VARIABLES <============
	
	@NotEmpty(message = "First name required")
	@Size(min = 2, max = 30, message = "First Name must be between 2 and 30 characters")
	private String firstName;
	
	@NotEmpty(message = "Last name required")
	@Size(min = 2, max = 30, message = "Last Name must be between 2 and 30 characters")
	private String lastName;
	
	@NotEmpty(message = "Email required")
	@Email(message = "Not a valid email address")
	private String email;
	
	@NotEmpty(message = "Password required")
	@Size(min = 8, max = 255, message = "Password must be at least 8 characters")
	private String password;
	
	@Transient
	@NotEmpty(message = "Please confirm password")
	@Size(min = 8, max = 255, message = "Password must be at least 8 characters")
	private String confirmPassword;
	
	// ---------------------------
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	// ============> RELATIONSHIPS <============
	
	@OneToMany(mappedBy = "lead", fetch = FetchType.LAZY)
	private List<Project> projectsLed;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_projects",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id")
			)
	private List<Project> projects;

	// ============> CONSTRUCTORS <============
	
	public User() {
//		this.projectsLed = new ArrayList<Project>();
//		this.projects = new ArrayList<Project>();
	}

	// ============> GETTERS & SETTERS <============
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	// ---------------------------
		// Getters:

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @return the projectsLed
	 */
	public List<Project> getProjectsLed() {
		return projectsLed;
	}

	/**
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}
	
	// ---------------------------
		// Setters:

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @param projectsLed the projectsLed to set
	 */
	public void setProjectsLed(List<Project> projectsLed) {
		this.projectsLed = projectsLed;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}