package org.doit.ik.domain.security;

import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.warn("@ @ @ @ CustomUserDetailService.loadUserByUsername()... username : "+username);
		MemberVO memberVO = null;
		try {
			memberVO = this.memberMapper.read(username);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("> CustomUserDetailService.loadUserByUsername() Exception...");
			e.printStackTrace();
		}
		return memberVO == null ? null : new CustomerUser(memberVO);
	}

}
