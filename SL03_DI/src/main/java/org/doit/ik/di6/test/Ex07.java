package org.doit.ik.di6.test;

import org.doit.ik.di6.AuthException;
import org.doit.ik.di6.AuthenticationService;
import org.doit.ik.di6.PasswordChangeService;
import org.doit.ik.di6.UserNotFoundException;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex07 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:org/doit/ik/di6/application-context.xml");
		AuthenticationService authSvc=
				ctx.getBean("authenticationService",AuthenticationService.class);
		
		System.out.println(authSvc.getUserRepository().getUserMap().size()); // 2
		
		/*
		runAuthAndCatchAuthEx(authSvc,"bkchoi","1111");
		runAuthAndCatchAuthEx(authSvc,"bkchoi","11111");
		runAuthAndCatchAuthEx(authSvc,"bkchoi","111111");
		try {
			authSvc.authenticate("bkchoi2", "1111");
		}catch(UserNotFoundException ex) {
			
		}
		authSvc.authenticate("bkchoi", "1234");
		PasswordChangeService pwChgSvc=
				ctx.getBean(PasswordChangeService.class);
		pwChgSvc.changePassword("bkchoi", "1234", "5678");
		*/
		ctx.close();
	}
	private static void runAuthAndCatchAuthEx(AuthenticationService authSvc,String userId, String password) {
		try {
			authSvc.authenticate(userId, password);
		}catch(AuthException ex) {
			
		}
	}
	

}
