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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@NotBlank
	@Size(min=2, max=25, message="Name must be between 2 and 40 chacarters.")
	private String name;
	@Email
	@NotBlank
    private String email;
	@Size(min=8, message="Password must be more than 8 characters long.")
	private String password;
	@Transient
    private String passwordConfirmation;
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @OneToMany(mappedBy="uses", fetch = FetchType.LAZY)
    private List<Idea> ideasss;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "likes",
    		joinColumns = @JoinColumn(name = "users_id"),
    		inverseJoinColumns = @JoinColumn(name = "ideas_id")
    )
    private List<Idea> ideas;
    
	public User() {
		super();
	}
	public User(Long id,
			@NotBlank @Size(min = 2, max = 25, message = "Name must be between 2 and 40 chacarters.") String name,
			@Email @NotBlank String email,
			@Size(min = 8, message = "Password must be more than 8 characters long.") String password,
			String passwordConfirmation, Date createdAt, Date updatedAt, List<Idea> ideasss, List<Idea> ideas) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.ideasss = ideasss;
		this.ideas = ideas;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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
	public List<Idea> getIdeasss() {
		return ideasss;
	}
	public void setIdeasss(List<Idea> ideasss) {
		this.ideasss = ideasss;
	}
	public List<Idea> getIdeas() {
		return ideas;
	}
	public void setIdeas(List<Idea> ideas) {
		this.ideas = ideas;
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
