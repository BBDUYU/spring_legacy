package org.doit.ik.aop4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex04 {

	public static void main(String[] args) {
		// 스프링 AOP 구현 3가지 방법중 @Aspect 어노테이션 기반
		// 226p
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:org/doit/ik/aop4/application-context.xml");
		Calculator4 calc = ctx.getBean("calc4",Calculator4.class);
		System.out.println(calc.add(4, 2));
		
		System.out.println(" END ");
		
		
	}
}
