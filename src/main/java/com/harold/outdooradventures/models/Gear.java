package com.harold.outdooradventures.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="gear")
public class Gear {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Name cannot be blank")
	@Size(max=100,message="Name length maximum 100 characters")
	private String gearName;
	
	@NotNull(message="Rating cannot be blank")
	@Min(value=1,message="Rate gear on a scale of 1-10")
	private Integer rating;
	
	@NotEmpty(message="Review cannot be blank")
	private String review;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private GearCategory gearCategory;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	public Gear() {
		
	}
	
	public Gear(String gearName, Integer rating, String review, Post post, GearCategory gearCategory) {
		this.gearName = gearName;
		this.rating = rating;
		this.review = review;
		this.post = post;
		this.gearCategory = gearCategory;
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

	public String getGearName() {
		return gearName;
	}

	public void setGearName(String gearName) {
		this.gearName = gearName;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public GearCategory getGearCategory() {
		return gearCategory;
	}

	public void setGearCategory(GearCategory gearCategory) {
		this.gearCategory = gearCategory;
	}
	
}
