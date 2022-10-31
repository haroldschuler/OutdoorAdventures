package com.harold.outdooradventures.models;

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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Name cannot be blank")
	@Size(max=100,message="Name length maximum 100 characters")
	private String title;
	
	@NotEmpty(message="Location cannot be blank")
	@Size(max=100, message="Location length maximum 100 characters")
	private String location;
	
	@NotNull(message="Start date cannot be empty")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@NotNull(message="End date cannot be empty")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="posts_with_activities",
		joinColumns= @JoinColumn(name="post_id"),
		inverseJoinColumns = @JoinColumn(name="activity_id")
	)
	private List<Activity> activities;
	
	@OneToMany(mappedBy="post", fetch = FetchType.LAZY)
	private List<Image> images;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@OneToMany(mappedBy="post", fetch = FetchType.LAZY)
	private List<Gear> gear;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	public Post() {
		
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Gear> getGear() {
		return gear;
	}

	public void setGear(List<Gear> gear) {
		this.gear = gear;
	}
	
}
