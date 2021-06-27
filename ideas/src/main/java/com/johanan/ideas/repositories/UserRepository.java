package com.johanan.ideas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.johanan.ideas.models.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findNameByIdeas(String idea);
	User findByEmail(String email);
	boolean existsByEmail(String email);
}
