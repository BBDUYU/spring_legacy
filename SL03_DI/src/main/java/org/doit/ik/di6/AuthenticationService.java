package org.doit.ik.di6;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
@Getter
public class AuthenticationService {
	private UserRepository userRepository;
	private AuthFailLogger failLogger;
	
	public AuthInfo authenticate(String id, String password) {
		User user = userRepository.findUserById(id);
		if(user==null)
			throw new UserNotFoundException();
		if(!user.matchPassword(password)){
			failLogger.insertBadPw(id, password);
			throw new AuthException();
		}
		return new AuthInfo(user.getId());
	}
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public void setFailLogger(AuthFailLogger failLogger) {
		this.failLogger=failLogger;
	}
}
