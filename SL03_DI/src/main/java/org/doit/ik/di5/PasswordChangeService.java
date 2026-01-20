package org.doit.ik.di5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PasswordChangeService {
	@Autowired
	private UserRepository userRepository;
	
	public PasswordChangeService(UserRepository userRepsitory) {
		this.userRepository=userRepsitory;
	}
	public void changePassword(String userId,String oldPw,String newPw) {
		User user = userRepository.findUserById(userId);
		if(user==null)
			throw new UserNotFoundException();
		user.changePassword(oldPw,newPw);
	}
}
