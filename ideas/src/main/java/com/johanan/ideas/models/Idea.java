package com.johanan.ideas.models;

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
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="idea")
public class Idea {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@NotBlank
	private String content;
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="uses_id")
	private User uses;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "likes",
    		joinColumns = @JoinColumn(name = "ideas_id"),
    		inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private List<User> likers;
    

	public Idea() {
		super();
	}
	public Idea(Long id, @NotBlank String content, Date createdAt, Date updatedAt, User uses, List<User> likers) {
		super();
		this.id = id;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.uses = uses;
		this.likers = likers;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getUses() {
		return uses;
	}
	public void setUses(User uses) {
		this.uses = uses;
	}
	public List<User> getLikers() {
		return likers;
	}
	public void setLikers(List<User> likers) {
		this.likers = likers;
	}
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
}
