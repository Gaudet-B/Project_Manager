package com.gaudetb.projectmanager.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "projects")
public class Project {

	// ============> PRIMARY KEY <============
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		// ============> MEMBER VARIABLES <============
		
		@NotEmpty(message = "Title required")
		@Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
		private String title;
		
		@NotEmpty(message = "Description required")
		@Size(min = 2, max = 250, message = "Description may not exceed 250 characters")
		private String description;
		
		@NotEmpty(message = "Due date Required")
//		@FutureOrPresent(message = "Due date must not be in the past")
		private String dueDate;
		
		// ---------------------------
		
		@Column(updatable = false)
		private Date createdAt;
		private Date updatedAt;
		
		// ============> RELATIONSHIPS <============
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id")
		private User lead;
		
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(
				name = "users_projects",
				joinColumns = @JoinColumn(name = "project_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id")
			)
		private List<User> users;
		
		// ============> CONSTRUCTORS <============
		
		public Project() {
//			this.users = new ArrayList<User>();
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
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * @return the dueDate
		 */
		public String getDueDate() {
			return dueDate;
		}

		/**
		 * @return the lead
		 */
		public User getLead() {
			return lead;
		}

		/**
		 * @return the users
		 */
		public List<User> getUsers() {
			return users;
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
		
		// ---------------------------
			// Setters:

		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * @param title the title to set
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}

		/**
		 * @param dueDate the dueDate to set
		 */
		public void setDueDate(String dueDate) {
			this.dueDate = dueDate;
		}

		/**
		 * @param lead the lead to set
		 */
		public void setLead(User lead) {
			this.lead = lead;
		}

		/**
		 * @param users the users to set
		 */
		public void setUsers(List<User> users) {
			this.users = users;
		}
}
