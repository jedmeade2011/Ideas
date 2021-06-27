package com.johanan.ideas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johanan.ideas.models.Idea;
import com.johanan.ideas.models.User;
import com.johanan.ideas.repositories.IdeaRepository;

@Service
public class GreatIdeaSerivce {
	@Autowired
	private IdeaRepository iRepo;
	
	public List<Idea> allIdeas(){
		return this.iRepo.findAll();
	}
	public List<Idea> getTopLikes(){
		return this.iRepo.findContentByOrderByLikersDesc();
	}
	public List<Idea> getBottomLikes(){
		return this.iRepo.findContentByOrderByLikersAsc();
	}
	public Idea getOneIdea(Long id) {
		return this.iRepo.findById(id).orElse(null);
	}
	public Idea createIdea(Idea newIdea) {
		return this.iRepo.save(newIdea);
	}
	public Idea updateIdea(Idea updateIdea) {
		return this.iRepo.save(updateIdea);
	}
	public void delete(Long id) {
		this.iRepo.deleteById(id);
	}
	public void likedUser(User user, Idea idea) {
		List <User> usersWhoLiked = idea.getLikers();
		usersWhoLiked.add(user);
		this.iRepo.save(idea);
	}
	public void unlikedUser(User user, Idea idea) {
		List<User> usersWhoLiked = idea.getLikers();
		usersWhoLiked.remove(user);
		this.iRepo.save(idea);
	}
}
