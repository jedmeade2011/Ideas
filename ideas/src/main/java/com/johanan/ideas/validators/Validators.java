package com.johanan.ideas.validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.johanan.ideas.models.User;
import com.johanan.ideas.repositories.UserRepository;

@Component
public class Validators {
	@Autowired
	private UserRepository usRepo;
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if(!user.getPassword().equals(user.getPasswordConfirmation())) {
			errors.rejectValue("password", "Match", "Passwords do not match!!");
		}
		if(this.usRepo.existsByEmail(user.getEmail())) {
			errors.rejectValue("email", "Unique", "Email exists in database.");
		}
	}
}
