package com.johanan.ideas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.johanan.ideas.models.Idea;
@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long>{
	List<Idea> findAll();
	List<Idea> findContentByOrderByLikersDesc();
	List<Idea> findContentByOrderByLikersAsc();
	}
