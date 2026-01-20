package org.doit.ik.aop2;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex02 {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:org/doit/ik/aop2/application-context.xml");
		Calculator2 calc = ctx.getBean("calcProxy",Calculator2.class);
//		Calculator2 calc = ctx.getBean("calc",Calculator2.class);
		System.out.println(calc.add(4, 2));
		
		System.out.println(" END ");
		
		// org.doit.ik.aop2.advice
		
		// 204 p 스프링 AOP
		/*
		 * 				[인증]	[권한]  핵심비즈니스로직기능		공통기능 
		 * 글목록		  X       X				  
		 * 글쓰기		  O       X				글쓰기				  인증
		 * 글수정         O       O				수정				  인증
		 * 글삭제         O       O				삭제				  인증
		 * 
		 * org.doit.ik.aop 패키지
		 * 	ㄴ Calculator.java 인터페이스
		 * 	ㄴ CalculatorImpl.java 클래스
		 *  ㄴ Ex01.java 클래스
		 * */
	}
}
