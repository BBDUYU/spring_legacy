package org.doit.ik.aop;

public class Ex01 {

	public static void main(String[] args) {
		
		Calculator calc=new CalculatorImpl();
		System.out.println(calc.add(4, 2));
		System.out.println(calc.sub(4, 2));
		System.out.println(calc.mul(4, 2));
		System.out.println(calc.div(4, 2));
		
		System.out.println(" END ");
		
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
